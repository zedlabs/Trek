package ml.zedlabs.data.network

import ml.zedlabs.domain.model.common.SearchListResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface JsonApi {

    @GET("/3/search/multi")
    suspend fun searchMultiList(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("page") page: Int,
    ): SearchListResponse


}