package ml.zedlabs.data.repository

import ml.zedlabs.data.BuildConfig
import ml.zedlabs.data.network.TvApi
import ml.zedlabs.data.util.handleRequest
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.common.TvExternalIdResponse
import ml.zedlabs.domain.model.common.UserReviewResponse
import ml.zedlabs.domain.model.tv.TvDetailResponse
import ml.zedlabs.domain.model.tv.TvListResponse
import ml.zedlabs.domain.model.tv.TvSeasonDetails
import ml.zedlabs.domain.repository.TvRepository
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor(
    val tvApiService: TvApi
): TvRepository {

    override suspend fun getTvShowList(listType: String, page: Int): Resource<TvListResponse> {
        return handleRequest {
            tvApiService.getTvShows(
                listType = listType,
                page = page,
                api_key = BuildConfig.APIKEY,
            )
        }
    }

    override suspend fun getTvShowDetails(tvId: Int): Resource<TvDetailResponse> {
        return handleRequest {
            tvApiService.getTvShowDetails(
                tv_id = tvId,
                api_key = BuildConfig.APIKEY,
            )
        }
    }

    override suspend fun getSimilarShows(tvId: Int, page: Int): Resource<TvListResponse> {
        return handleRequest {
            tvApiService.getSimilarTvShows(
                tv_id = tvId,
                page = page,
                api_key = BuildConfig.APIKEY,
            )
        }
    }

    override suspend fun getUserTvReviews(tvId: Int, page: Int): Resource<UserReviewResponse> {
        return handleRequest {
            tvApiService.getUserTvReviews(
                tv_id = tvId,
                page = page,
                api_key = BuildConfig.APIKEY,
            )
        }
    }

    override suspend fun getTvSeasonDetails(tvId: Int, seasonNumber: Int): Resource<TvSeasonDetails> {
        return handleRequest {
                tvApiService.getTvSeasonDetails(
                    tv_id = tvId,
                    season_number = seasonNumber,
                    api_key = BuildConfig.APIKEY,
                )
        }
    }

    override suspend fun getTvExternalIds(tvId: Int): Resource<TvExternalIdResponse> {
        return handleRequest {
            tvApiService.getTvExternalIds(
                tv_id = tvId,
                api_key = BuildConfig.APIKEY,
            )
        }
    }

}