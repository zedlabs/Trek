package ml.zedlabs.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ml.zedlabs.data.util.Constants.imdb_rating_class_id
import ml.zedlabs.data.util.createImdbUrlFromImdbMediaId
import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.repository.WebParsingRepository
import org.jsoup.Jsoup
import java.lang.Exception

class WebParsingRepositoryImpl: WebParsingRepository {

    override suspend fun getImbdShowRating(imdbId: String): Resource<Double> {
        val ratingDouble: Double
        try {
            val webPage = withContext(Dispatchers.IO){
                return@withContext Jsoup.connect(imdbId.createImdbUrlFromImdbMediaId()).get()
            }
            val ratingElement = webPage.getElementsByClass(imdb_rating_class_id)
            val rating = ratingElement.first()?.text()
            ratingDouble = rating?.toDouble() ?: 0.0
        } catch (e: Exception) {
            /**
             * use generic exception as multiple exceptions possible
             * (Web, Url, NumberFormatting)
             */
            return Resource.Error(Throwable(e))
        }
            return Resource.Success(data = ratingDouble)
    }
}