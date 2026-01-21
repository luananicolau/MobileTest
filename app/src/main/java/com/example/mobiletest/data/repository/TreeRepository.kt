package com.example.mobiletest.repositories

import com.example.mobiletest.database.dao.TreeDao
import com.example.mobiletest.database.TreeNodeEntity
import com.example.mobiletest.data.mapper.toEntity
import com.example.mobiletest.data.mapper.toTreeNode
import com.example.mobiletest.services.TreeService
import com.example.mobiletest.states.TreeUiState
import javax.inject.Inject

interface TreeRepository {

    suspend fun getTree(
        token: String,
        siteId: Int
    ): TreeUiState<List<TreeNode>>

    suspend fun updateNodeName(
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

                if (response.isSuccessful && response.body() != null) {
                    val entities = response.body()!!.tree.map { it.toEntity() }
                    dao.insertAll(entities)
                    storedEntities = dao.getAll(siteId)
                } else {
                    return TreeUiState.Error(
                        message = "Erro ao buscar árvore na rede",
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

    override suspend fun updateNodeName(
        nodeId: Int,
        newName: String
    ) {
        dao.updateNodeName(nodeId, newName)
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

