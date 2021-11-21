package ml.zedlabs.domain.usecases

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.movie.MovieDetailResponse
import ml.zedlabs.domain.repository.MovieRepository

class GetMovieDetailUseCase(
    val movieRepository: MovieRepository
) {
    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailResponse> {
        return movieRepository.getMovieDetails(movieId)
    }
}