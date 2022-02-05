package ml.zedlabs.domain.model.common

enum class MediaSortingParam(val query: String) {
    TOP_RATED("top_rated"),
    UPCOMING("upcoming"),
    POPULAR("popular"),
    NOW_PLAYING("now_playing"),
}