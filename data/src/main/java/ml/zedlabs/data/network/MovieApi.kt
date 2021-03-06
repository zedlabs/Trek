package ml.zedlabs.data.network

import ml.zedlabs.domain.model.common.UserReviewResponse
import ml.zedlabs.domain.model.movie.MovieDetailResponse
import ml.zedlabs.domain.model.movie.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/movie/{listType}")
    suspend fun getImdbTopRatedMovieList(
        @Path("listType") listType: String,
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
        @Query("region") region: String?,
    ): Response<MovieListResponse>

    @GET("/3/movie/{movie_id}/similar")
    suspend fun getSimilarMovieList(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): Response<MovieListResponse>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
    ): Response<MovieDetailResponse>

    @GET("/3/movie/{movie_id}/reviews")
    suspend fun getUserMovieReview(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): Response<UserReviewResponse>


    @GET("/3/movie/{movie_id}/recommendations")
    suspend fun getUserMovieRecommendations(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): Response<MovieListResponse>
}