package ml.zedlabs.domain.model.common

data class MediaDetailCommon(
    val mediaId: Int,
    val posterPath: String?,
    val title: String?,
    val description: String?,
    val formattedRating: String?,
    val formattedVoteCount: String?,
    val type: MediaType
)

enum class MediaType {
    MOVIE,
    TV,
    ANIME,
}
