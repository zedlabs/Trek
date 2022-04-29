package ml.zedlabs.domain.usecases

import ml.zedlabs.domain.repository.WebParsingRepository

class GetWebScrapingDataUseCase(
    val webParsingRepository: WebParsingRepository
) {

    suspend fun getImdbRating(imdbId: String) = webParsingRepository.getImbdShowRating(imdbId)

}