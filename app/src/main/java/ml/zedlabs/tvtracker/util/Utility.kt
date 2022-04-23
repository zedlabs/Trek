package ml.zedlabs.tvtracker.util

import android.content.Context
import android.widget.Toast
import ml.zedlabs.domain.model.common.MediaDetailCommon
import ml.zedlabs.domain.model.common.MediaType
import ml.zedlabs.domain.model.movie.MovieDetailResponse
import ml.zedlabs.domain.model.tv.TvDetailResponse
import ml.zedlabs.tvtracker.util.Constants.IMAGE_W500_BASE

fun String.appendAsImageUrl(): String = "$IMAGE_W500_BASE$this"

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun MovieDetailResponse.mapToMediaCommon() : MediaDetailCommon{
    return with(this) {
        MediaDetailCommon(
            mediaId = id,
            title = title,
            description = overview,
            posterPath = poster_path,
            formattedRating = vote_average.asRating(),
            formattedVoteCount = vote_count.toString(),
            type = MediaType.MOVIE,
        )
    }
}

fun TvDetailResponse.mapToMediaCommon() : MediaDetailCommon{
    return with(this) {
        MediaDetailCommon(
            mediaId = id,
            title = name,
            description = overview,
            posterPath = poster_path,
            formattedRating = vote_average.asRating(),
            formattedVoteCount = vote_count.toString(),
            type = MediaType.TV,
        )
    }
}

fun Double.asRating() : String {
    return "$this/ 10"
}