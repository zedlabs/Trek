package ml.zedlabs.domain.usecases

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.tv.TvListResponse
import ml.zedlabs.domain.repository.TvRepository

class GetTvListUseCase(
    val tvRepository: TvRepository
) {

    suspend fun getTvShowList(listType: String, page: Int): Resource<TvListResponse> {
        return tvRepository.getTvShowList(listType, page)
    }

    suspend fun getSimilarShows(tvId: Int, page: Int) : Resource<TvListResponse> {
        return tvRepository.getSimilarShows(tvId, page)
    }

}