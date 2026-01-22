package com.example.mobiletest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TreeSyncRequest(
    val revision: String,
    val nodes: List<SyncNode>
)