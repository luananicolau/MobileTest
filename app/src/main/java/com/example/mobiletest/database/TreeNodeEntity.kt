package com.example.mobiletest.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tree_nodes")
data class TreeNodeEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tag: String?,
    val level: Int?,
    val parentId: Int?,
    val siteId: Int?
)
