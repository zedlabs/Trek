package ml.zedlabs.data.repository

import ml.zedlabs.data.BuildConfig
import ml.zedlabs.data.network.MovieApi
import ml.zedlabs.data.util.handleRequest
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.movie.TopRatedMoviesResponse
import ml.zedlabs.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    val movieApiService: MovieApi
) : MovieRepository {

    override suspend fun getImdbTopRatedMovieList(): Resource<TopRatedMoviesResponse> {

        return handleRequest {
            movieApiService.getImdbTopRatedMovieList(
                api_key = BuildConfig.APIKEY,
                page = 1
            )
        }
    }
}