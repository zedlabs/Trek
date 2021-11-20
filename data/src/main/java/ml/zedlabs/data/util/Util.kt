package ml.zedlabs.data.util

import ml.zedlabs.domain.model.Resource
import ml.zedlabs.domain.model.Resource.*
import retrofit2.Response

/**
 * function to handle api request errors & parse the response to a Resource object
 */
suspend fun <T: Any> handleRequest(requestFunc: suspend () -> Response<T>): Resource<T> {

    return try {
        val response = requestFunc.invoke()
        response.body()?.let { res ->
            if(response.isSuccessful) {
                return Success(res)
            }
            return Error(Throwable(response.message()))
        }
        return Error(Throwable(response.message()))
    } catch (exception: Exception) {
        return Error(exception)
    }
}