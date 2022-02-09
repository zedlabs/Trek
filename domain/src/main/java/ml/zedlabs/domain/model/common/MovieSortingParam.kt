package ml.zedlabs.domain.model.common

enum class MovieSortingParam(val query: String) {
    TOP_RATED("top_rated"),
    UPCOMING("upcoming"),
    POPULAR("popular"),
    NOW_PLAYING("now_playing"),
}

enum class TvSortingParam(val query: String) {
    TOP_RATED("top_rated"),
    ON_THE_AIR("on_the_air"),
    POPULAR("popular"),
}