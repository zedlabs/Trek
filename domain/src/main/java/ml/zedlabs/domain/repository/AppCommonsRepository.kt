package ml.zedlabs.domain.repository

import kotlinx.coroutines.flow.Flow
import ml.zedlabs.domain.model.common.AddedList
import ml.zedlabs.domain.model.common.SearchListResponse

interface AppCommonsRepository {

    // search
    suspend fun searchMultiList(query: String, page: Int) : SearchListResponse

    fun getUserAddedList(): Flow<List<AddedList>>

    suspend fun addToUserAddedList(item: AddedList)

    suspend fun deleteAllEntries()
    // trending

}