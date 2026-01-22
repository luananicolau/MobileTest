
package com.example.mobiletest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SyncNode(
    val id: Int,
    val name: String,
    val tag: String = "",
    val level: Int,
    val status: Boolean = true,
    val order: Int,
    val parent: Int,
    val altered: Int,
    val group: String = "",
    val site: Int
)