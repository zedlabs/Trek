package ml.zedlabs.domain.usecases

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.movie.MovieListResponse
import ml.zedlabs.domain.repository.AppCommonsRepository

class SearchUseCase(
    val appCommonsRepository: AppCommonsRepository
) {

    suspend fun search(query: String, page: Int): Resource<MovieListResponse> {
        return appCommonsRepository.searchMultiList(query, page)
    }

}