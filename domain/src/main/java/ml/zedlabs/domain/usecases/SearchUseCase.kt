package ml.zedlabs.domain.usecases

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.common.SearchListResponse
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.repository.AppCommonsRepository

class SearchUseCase(
    val appCommonsRepository: AppCommonsRepository
) {

    suspend fun search(query: String, page: Int): SearchListResponse {
        return appCommonsRepository.searchMultiList(query, page)
    }

}