package ml.zedlabs.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
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
) : AppCommonsRepository {

    override suspend fun searchMultiList(query: String, page: Int): SearchListResponse =
        jsonApi.searchMultiList(BuildConfig.APIKEY, query, page)

    override fun getUserAddedList() =
        addedListDao.getAll().flowOn(Dispatchers.Main).map { userList ->
            userList.map {
                AddedList(
                    uid = it.uid,
                    title = it.title,
                    description = it.description,
                    posterPath = it.posterPath,
                )
            }
        }.conflate()

    override suspend fun addToUserAddedList(item: AddedList) {
        // map to data model; ideally there should be mapper functions for this purpose
        addedListDao.insertAll(
            ml.zedlabs.data.local_db.AddedList(
                uid = item.uid,
                title = item.title,
                description = item.description,
                posterPath = item.posterPath,
            )
        )
    }

    override suspend fun deleteAllEntries() {
        addedListDao.deleteAll()
    }

}