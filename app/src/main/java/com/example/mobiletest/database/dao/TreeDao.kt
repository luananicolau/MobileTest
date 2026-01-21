package com.example.mobiletest.database.dao

import androidx.room.*
import com.example.mobiletest.database.TreeNodeEntity

@Dao
interface TreeDao {

    @Query("SELECT * FROM tree_nodes WHERE siteId = :siteId")
    suspend fun getAll(siteId: Int): List<TreeNodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(nodes: List<TreeNodeEntity>)

    @Query("UPDATE tree_nodes SET name = :newName WHERE id = :nodeId")
    suspend fun updateNodeName(nodeId: Int, newName: String)
}
