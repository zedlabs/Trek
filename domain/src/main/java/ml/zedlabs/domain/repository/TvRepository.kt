package ml.zedlabs.domain.repository

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.common.TvExternalIdResponse
import ml.zedlabs.domain.model.common.UserReviewResponse
import ml.zedlabs.domain.model.tv.TvDetailResponse
import ml.zedlabs.domain.model.tv.TvListResponse
import ml.zedlabs.domain.model.tv.TvSeasonDetails

interface TvRepository {

    suspend fun getTvShowList(listType: String, page: Int) : Resource<TvListResponse>

    suspend fun getTvShowDetails(tvId: Int) : Resource<TvDetailResponse>

    suspend fun getSimilarShows(tvId: Int, page: Int): Resource<TvListResponse>

    suspend fun getUserTvReviews(tvId: Int, page: Int) : Resource<UserReviewResponse>

    suspend fun getTvSeasonDetails(tvId: Int, seasonNumber: Int) : Resource<TvSeasonDetails>

    suspend fun getTvExternalIds(tvId: Int) : Resource<TvExternalIdResponse>

    // - - - details

    // - - - reviews

    // - - - similar

    // watch providers i.e streaming services

    // - - - popular

    // - - - top rated

    // - - - on air

    // - - - tv season details (comes with necessary episode details)
}