package com.example.mobiletest.services

import com.example.mobiletest.data.model.TreeSyncRequest
import com.example.mobiletest.utils.ApiUrls
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface TreeService {
    @GET(ApiUrls.TREE)
    suspend fun getTree(
        @Header("Authorization") token: String,
        @Query("site") siteId: Int
    ): Response<SiteResponse>

    @POST("implantation/mobile/tree")
    suspend fun syncTree(
        @Header("Authorization") token: String,
        @Query("site") siteId: Int, // O servidor EXIGE isso aqui na URL
        @Body body: TreeSyncRequest
    ): Response<Unit>

    @PATCH("implantation/mobile/tree") // Verifique se esta rota existe na doc
    suspend fun updateAssetName(
        @Header("Authorization") token: String,
        @Path("id") assetId: Int,
        @Body body: UpdateAssetRequest
    ): Response<Unit>

}
@Serializable
data class UpdateAssetRequest(
    val name: String
)

@Serializable
data class SiteResponse(
    val id: Int,
    val name: String,
    val revision: Int,
    val tree: List<AssetResponse>,
)

@Serializable
data class AssetResponse(
    val id: Int,
    @SerialName("refresh_setups") val refreshSetups: Boolean,
    @SerialName("asset_type") val assetType: Int?,
    @SerialName("original_asset_type") val originalAssetType: Int?,
    val group: String?,
    val criticality: String?,
    @SerialName("functional_location") val functionalLocation: String?,
    val status: Boolean?,
    val name: String,
    val tag: String?,
    val level: Int?,
    val order: Int,
    val parent: Int?,
    val site: Int?,
)

