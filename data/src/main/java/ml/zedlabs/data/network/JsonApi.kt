package ml.zedlabs.data.network

import ml.zedlabs.domain.model.movie.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface JsonApi {

    @GET("/3/search/multi")
    suspend fun searchMultiList(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("page") page: Int,
    ): Response<MovieListResponse>


}