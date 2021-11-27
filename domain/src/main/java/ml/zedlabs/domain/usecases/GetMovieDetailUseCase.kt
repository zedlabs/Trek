package ml.zedlabs.domain.usecases

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.common.UserReviewResponse
import ml.zedlabs.domain.model.movie.MovieDetailResponse
import ml.zedlabs.domain.repository.MovieRepository

class GetMovieDetailUseCase(
    val movieRepository: MovieRepository
) {
    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailResponse> {
        return movieRepository.getMovieDetails(movieId)
    }

    suspend fun getUserMovieReview(movieId: Int, page: Int) : Resource<UserReviewResponse> {
        return movieRepository.getUserMovieReviews(movieId, page)
    }
}