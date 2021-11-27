package ml.zedlabs.domain.usecases

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.repository.MovieRepository

class GetMovieListUseCase (
    val movieRepository: MovieRepository
) {

    suspend fun getMovieList(listType: String, page: Int): Resource<MovieListResponse> {
        return movieRepository.getMovieList(listType, page)
    }

    suspend fun getSimilarMovies(movieId: Int, page: Int) : Resource<MovieListResponse> {
        return movieRepository.getSimilarMovies(movieId, page)
    }

    suspend fun getRecommendedMovies(movieId: Int, page: Int) : Resource<MovieListResponse> {
        return movieRepository.getRecommendedMovies(movieId, page)
    }

}