package ml.zedlabs.domain.repository

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.tv.TvListResponse

interface WebParsingRepository {

    suspend fun getImbdShowRating(imdbId: String) : Resource<Double>

}