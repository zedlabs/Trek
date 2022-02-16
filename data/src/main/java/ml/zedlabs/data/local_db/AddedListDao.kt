package ml.zedlabs.data.local_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AddedListDao {
    @Query("SELECT * FROM addedlistitem")
    fun getAll(): List<AddedListItem>

    @Query("SELECT * FROM addedlistitem WHERE uid IN (:mediaIds)")
    fun loadAllByIds(mediaIds: IntArray): List<AddedListItem>

    @Query("SELECT * FROM addedlistitem WHERE media_name LIKE :name")
    fun findByName(name: String): AddedListItem

    @Insert
    fun insertAll(vararg items: AddedListItem)

    @Delete
    fun delete(items: AddedListItem)
}
