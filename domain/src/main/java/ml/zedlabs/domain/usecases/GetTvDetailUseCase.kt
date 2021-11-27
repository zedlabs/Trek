package ml.zedlabs.domain.usecases

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.common.UserReviewResponse
import ml.zedlabs.domain.model.tv.TvDetailResponse
import ml.zedlabs.domain.repository.TvRepository

class GetTvDetailUseCase(
    val tvRepository: TvRepository
) {

    suspend fun getTvShowDetails(tvId: Int): Resource<TvDetailResponse> {
        return tvRepository.getTvShowDetails(tvId)
    }

    suspend fun getTvShowReviews(tvId: Int, page: Int): Resource<UserReviewResponse> {
        return tvRepository.getUserTvReviews(tvId, page)
    }

}