package ml.zedlabs.data.local_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AddedListDao {
    @Query("SELECT * FROM addedlist")
    fun getAll(): Flow<List<AddedList>>

    @Query("SELECT * FROM addedlist WHERE mediaId IN (:mediaIds)")
    suspend fun loadAllByIds(mediaIds: IntArray): List<AddedList>?

    @Query("SELECT * FROM addedlist WHERE media_id = :mediaId")
    suspend fun getById(mediaId: Int): AddedList?

    @Query("SELECT * FROM addedlist WHERE media_name LIKE :name")
    suspend fun findByName(name: String): AddedList?

    @Insert
    suspend fun insertAll(vararg items: AddedList)

    @Delete
    suspend fun delete(items: AddedList)

    @Query("DELETE FROM addedlist")
    suspend fun deleteAll()
}
