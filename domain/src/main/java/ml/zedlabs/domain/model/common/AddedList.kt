package ml.zedlabs.domain.model.common

data class AddedList(
    val uid: Int,
    val title: String?,
    val description: String?,
    val posterPath: String?,
    val type: MediaType,
)
