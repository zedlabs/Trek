package ml.zedlabs.data.network

import ml.zedlabs.domain.model.movie.TopRatedMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/movie/top_rated")
    suspend fun getImdbTopRatedMovieList(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): Response<TopRatedMoviesResponse>

}