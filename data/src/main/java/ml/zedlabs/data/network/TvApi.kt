package ml.zedlabs.data.network

import ml.zedlabs.domain.model.common.UserReviewResponse
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.model.tv.TvDetailResponse
import ml.zedlabs.domain.model.tv.TvListResponse
import ml.zedlabs.domain.model.tv.TvSeasonDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvApi {

    @GET("/3/tv/{listType}")
    suspend fun getTvShows(
        @Path("listType") listType: String,
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): Response<TvListResponse>

    @GET("/3/tv/{tv_id}/similar")
    suspend fun getSimilarTvShows(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): Response<TvListResponse>

    @GET("/3/tv/{tv_id}")
    suspend fun getTvShowDetails(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String,
    ): Response<TvDetailResponse>

    @GET("/3/tv/{tv_id}/season/{season_number}")
    suspend fun getTvSeasonDetails(
        @Path("tv_id") tv_id: Int,
        @Path("season_number") season_number: Int,
        @Query("api_key") api_key: String,
    ): Response<TvSeasonDetails>

    @GET("/3/tv/{tv_id}/reviews")
    suspend fun getUserTvReviews(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): Response<UserReviewResponse>

}