package ml.zedlabs.data.repository

import ml.zedlabs.data.BuildConfig
import ml.zedlabs.data.network.JsonApi
import ml.zedlabs.data.util.handleRequest
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.repository.AppCommonsRepository
import javax.inject.Inject

class AppCommonsRepositoryImpl @Inject constructor(
    val jsonApi: JsonApi
): AppCommonsRepository {

    override suspend fun searchMultiList(query: String, page: Int): Resource<MovieListResponse> {
        return handleRequest {
            jsonApi.searchMultiList(BuildConfig.APIKEY, query, page)
        }
    }
}