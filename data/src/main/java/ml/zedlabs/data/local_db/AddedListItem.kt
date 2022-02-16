package ml.zedlabs.data.local_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AddedListItem(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "media_name") val title: String?,
    @ColumnInfo(name = "media_desc") val description: String?,
    @ColumnInfo(name = "poster_path") val posterPath: String?,
)
