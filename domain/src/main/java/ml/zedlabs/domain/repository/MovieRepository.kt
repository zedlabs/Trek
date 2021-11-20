package ml.zedlabs.domain.repository

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.movie.TopRatedMoviesResponse

interface MovieRepository {

    // get popular movie list

    // get top rated movie list
    suspend fun getImdbTopRatedMovieList() : Resource<TopRatedMoviesResponse>

    // get particular movie details

    // get upcoming movies

    // get now playing movie details

    // get similar movies

    // get reviews

    // get recommendations
}