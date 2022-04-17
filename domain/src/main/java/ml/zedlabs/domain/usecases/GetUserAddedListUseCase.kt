package ml.zedlabs.domain.usecases

import kotlinx.coroutines.flow.Flow
import ml.zedlabs.domain.model.common.AddedList
import ml.zedlabs.domain.repository.AppCommonsRepository

class GetUserAddedListUseCase(
    val appCommonsRepository: AppCommonsRepository
) {

    fun getUserAddedList(): Flow<List<AddedList>> {
        return appCommonsRepository.getUserAddedList()
    }

    suspend fun addToUserAddedList(item: AddedList) {
        appCommonsRepository.addToUserAddedList(item)
    }

    suspend fun deleteAllItems() {
        appCommonsRepository.deleteAllEntries()
    }

}