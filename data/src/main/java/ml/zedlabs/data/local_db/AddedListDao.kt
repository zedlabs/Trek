package ml.zedlabs.data.local_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AddedListDao {
    @Query("SELECT * FROM addedlist")
    fun getAll(): List<AddedList>

    @Query("SELECT * FROM addedlist WHERE uid IN (:mediaIds)")
    fun loadAllByIds(mediaIds: IntArray): List<AddedList>

    @Query("SELECT * FROM addedlist WHERE media_name LIKE :name")
    fun findByName(name: String): AddedList

    @Insert
    fun insertAll(vararg items: AddedList)

    @Delete
    fun delete(items: AddedList)
}
