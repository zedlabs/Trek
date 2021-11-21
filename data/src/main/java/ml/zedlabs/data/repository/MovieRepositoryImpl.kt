package ml.zedlabs.data.repository

import ml.zedlabs.data.BuildConfig
import ml.zedlabs.data.network.MovieApi
import ml.zedlabs.data.util.handleRequest
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.movie.MovieDetailResponse
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    val movieApiService: MovieApi
) : MovieRepository {

    override suspend fun getMovieList(listType: String, page: Int): Resource<MovieListResponse> {
        return handleRequest {
            movieApiService.getImdbTopRatedMovieList(
                listType = listType,
                api_key = BuildConfig.APIKEY,
                page = page,
                region = null
            )
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailResponse> {
        return handleRequest {
            movieApiService.getMovieDetails(
                movie_id = movieId,
                api_key = BuildConfig.APIKEY
            )
        }
    }

    override suspend fun getSimilarMovies(
        movieId: Int,
        page: Int
    ): Resource<MovieListResponse> {
        return handleRequest {
            movieApiService.getSimilarMovieList(
                api_key = BuildConfig.APIKEY,
                movie_id = movieId,
                page = page
            )
        }
    }
}