package ml.zedlabs.data.repository

import ml.zedlabs.data.BuildConfig
import ml.zedlabs.data.local_db.AddedListDao
import ml.zedlabs.data.network.JsonApi
import ml.zedlabs.data.util.handleRequest
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.common.AddedList
import ml.zedlabs.domain.model.common.SearchListResponse
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.repository.AppCommonsRepository
import javax.inject.Inject

class AppCommonsRepositoryImpl @Inject constructor(
    val jsonApi: JsonApi,
    val addedListDao: AddedListDao
): AppCommonsRepository {

    override suspend fun searchMultiList(query: String, page: Int): SearchListResponse =
        jsonApi.searchMultiList(BuildConfig.APIKEY, query, page)

    override suspend fun getUserAddedList(): List<AddedList> {
        // return domain mapped response
        return addedListDao.getAll().map {
            AddedList(
                uid = it.uid,
                title = it.title,
                description = it.description,
                posterPath = it.posterPath,
            )
        }
    }

}