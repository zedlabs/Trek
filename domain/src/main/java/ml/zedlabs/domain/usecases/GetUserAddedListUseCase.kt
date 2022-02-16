package ml.zedlabs.domain.usecases

import ml.zedlabs.domain.model.common.AddedList
import ml.zedlabs.domain.repository.AppCommonsRepository

class GetUserAddedListUseCase(
    val appCommonsRepository: AppCommonsRepository
) {

    suspend fun getUserAddedList(): List<AddedList> {
        return appCommonsRepository.getUserAddedList()
    }

}