package ml.zedlabs.domain.repository

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.common.SearchListResponse
import ml.zedlabs.domain.model.movie.MovieListResponse

interface AppCommonsRepository {

    // search
    suspend fun searchMultiList(query: String, page: Int) : SearchListResponse

    // trending

}