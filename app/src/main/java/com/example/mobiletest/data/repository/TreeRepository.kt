package com.example.mobiletest.repositories

import com.example.mobiletest.database.dao.TreeDao
import com.example.mobiletest.database.TreeNodeEntity
import com.example.mobiletest.data.mapper.toEntity
import com.example.mobiletest.data.mapper.toTreeNode
import com.example.mobiletest.data.model.SyncNode
import com.example.mobiletest.data.model.TreeSyncRequest
import com.example.mobiletest.services.TreeService
import com.example.mobiletest.states.TreeUiState
import javax.inject.Inject
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


interface TreeRepository {

    suspend fun getTree(
        token: String,
        siteId: Int
    ): TreeUiState<List<TreeNode>>

    suspend fun updateNodeName(
        token: String,
        nodeId: Int,
        newName: String
    )
}
data class TreeNode(
    val id: Int,
    val name: String,
    val tag: String?,
    val type: String?,
    val level: Int?,
    val order: Int,
    val children: List<TreeNode> = emptyList(),
)


class TreeRepositoryImpl @Inject constructor(
    private val service: TreeService,
    private val dao: TreeDao
) : TreeRepository {

    override suspend fun getTree(
        token: String,
        siteId: Int
    ): TreeUiState<List<TreeNode>> {
        return try {
            var storedEntities = dao.getAll(siteId)

            if (storedEntities.isEmpty()) {
                val response = service.getTree(token, siteId)
                val body = response.body()

                if (response.isSuccessful && body != null) {
                    val currentRevision = body.revision

                    val entities = body.tree.map { nodeDto ->
                        nodeDto.toEntity().copy(
                            revision = currentRevision,
                            siteId = siteId,
                            group = nodeDto.group
                        )
                    }

                    dao.insertAll(entities)
                    storedEntities = dao.getAll(siteId)
                } else {
                    return TreeUiState.Error(
                        message = "Erro ao buscar árvore na rede: ${response.message()}",
                        code = response.code()
                    )
                }
            }

            val tree = buildTreeFromEntities(storedEntities)
            TreeUiState.Success(tree)

        } catch (e: Exception) {
            TreeUiState.Error("Falha na sincronização: ${e.message}")
        }
    }

    override suspend fun updateNodeName(token: String, nodeId: Int, newName: String) {
        try {
            val responseGet = service.getTree(token, 20640)

            if (!responseGet.isSuccessful) {
                android.util.Log.e("DEBUG_SYNC", "Falha ao buscar revisão: ${responseGet.errorBody()?.string()}")
                return
            }

            val latestTree = responseGet.body()
            val currentRevision = latestTree?.revision?.toString()?.toIntOrNull() ?: 0

            val entity = dao.getById(nodeId) ?: return

            //  monta o envio com a revisão do portal + 1
            val syncNode = SyncNode(
                id = entity.id,
                name = newName,
                altered = 1,
                site = 20640,
                parent = entity.parentId ?: 0,
                level = entity.level ?: 0,
                order = entity.order,
                status = true,
                group = entity.group ?: ""
            )

            val syncRequest = TreeSyncRequest(
                revision = (currentRevision + 1).toString(),
                nodes = listOf(syncNode)
            )

            android.util.Log.d("JSON_ENVIADO", "Revision: ${syncRequest.revision} | Node: $newName")

            val responsePost = service.syncTree(token, 20640, syncRequest)

            if (responsePost.isSuccessful) {
                android.util.Log.d("DEBUG_SYNC", "Sucesso! Portal atualizado para a revisão ${currentRevision + 1}")
                dao.updateNameAndRevision(nodeId, newName, currentRevision + 1)
            } else {
                android.util.Log.e("DEBUG_SYNC", "Erro no Sync: ${responsePost.errorBody()?.string()}")
            }

        } catch (e: Exception) {
            android.util.Log.e("DEBUG_SYNC", "Exceção: ${e.message}")
        }
    }
}




private fun buildTreeFromEntities(
    entities: List<TreeNodeEntity>
): List<TreeNode> {

    fun build(parentId: Int?): List<TreeNode> =
        entities
            .filter { it.parentId == parentId }
            .sortedBy { it.id }
            .map { entity ->
                entity.toTreeNode(
                    children = build(entity.id)
                )
            }

    return build(null)
}

