package com.example.mobiletest.data.mapper

import com.example.mobiletest.database.TreeNodeEntity
import com.example.mobiletest.repositories.TreeNode
import com.example.mobiletest.services.AssetResponse

fun AssetResponse.toEntity(): TreeNodeEntity =
    TreeNodeEntity(
        id = id,
        name = name,
        tag = tag,
        level = level,
        parentId = parent,
        siteId = site
    )

fun TreeNodeEntity.toTreeNode(children: List<TreeNode> = emptyList()): TreeNode =
    TreeNode(
        id = id,
        name = name,
        tag = tag ?: "",
        children = children,
        type = if (parentId == null) "SITE" else "ASSET",
        level = level ?: 0,
        order = 0
    )