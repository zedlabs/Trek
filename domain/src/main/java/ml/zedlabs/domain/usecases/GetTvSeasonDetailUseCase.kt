package ml.zedlabs.domain.usecases

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.tv.TvSeasonDetails
import ml.zedlabs.domain.repository.TvRepository

class GetTvSeasonDetailUseCase(
    val tvRepository: TvRepository
) {

    suspend fun getTvSeasonDetails(tvId: Int, seasonNumber: Int): Resource<TvSeasonDetails> {
        return tvRepository.getTvSeasonDetails(tvId, seasonNumber)
    }
}