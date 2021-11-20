package ml.zedlabs.domain.usecases

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.movie.TopRatedMoviesResponse
import ml.zedlabs.domain.repository.MovieRepository

class GetMovieListUseCase (
    val movieRepository: MovieRepository
) {

    suspend fun getImdbTopRatedMovieList(): Resource<TopRatedMoviesResponse> {
        return movieRepository.getImdbTopRatedMovieList()
    }
}