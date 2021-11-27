package ml.zedlabs.domain.repository

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.common.UserReviewResponse
import ml.zedlabs.domain.model.movie.MovieDetailResponse
import ml.zedlabs.domain.model.movie.MovieListResponse

interface MovieRepository {

    // get top popular, rated, upcoming, now playing movie list
    suspend fun getMovieList(listType: String, page: Int) : Resource<MovieListResponse>

    // get particular movie details
    suspend fun getMovieDetails(movieId: Int) : Resource<MovieDetailResponse>

    // get similar movies
    suspend fun getSimilarMovies(movieId: Int, page: Int): Resource<MovieListResponse>

    // get reviews
    suspend fun getUserMovieReviews(movieId: Int, page: Int) : Resource<UserReviewResponse>

    // get recommendations
    suspend fun getRecommendedMovies(movieId: Int, page: Int): Resource<MovieListResponse>

}