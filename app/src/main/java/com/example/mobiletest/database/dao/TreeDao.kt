package com.example.mobiletest.database.dao

import androidx.room.*
import com.example.mobiletest.database.TreeNodeEntity

@Dao
interface TreeDao {

    @Query("SELECT * FROM tree_nodes WHERE siteId = :siteId")
    suspend fun getAll(siteId: Int): List<TreeNodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(nodes: List<TreeNodeEntity>)

    @Query("UPDATE tree_nodes SET name = :newName, revision = :newRevision WHERE id = :nodeId")
    suspend fun updateNodeWithRevision(nodeId: Int, newName: String, newRevision: Int)

    @Query("SELECT * FROM tree_nodes WHERE id = :nodeId LIMIT 1")
    suspend fun getById(nodeId: Int): TreeNodeEntity?

    @Query("UPDATE tree_nodes SET name = :newName, revision = :newRevision WHERE id = :nodeId")
    suspend fun updateNameAndRevision(nodeId: Int, newName: String, newRevision: Int)

    @Query("DELETE FROM tree_nodes WHERE siteId = :siteId")
    suspend fun deleteBySiteId(siteId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TreeNodeEntity)

    @Query("UPDATE tree_nodes SET name = :newName, revision = :newRev, isPending = :pending WHERE id = :id")
    suspend fun updateWithPendingStatus(id: Int, newName: String, newRev: Int, pending: Boolean)


}