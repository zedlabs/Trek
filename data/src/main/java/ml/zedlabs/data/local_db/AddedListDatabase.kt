package ml.zedlabs.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AddedList::class], version = 2)
abstract class AddedListDatabase : RoomDatabase() {
    abstract fun addedListDao(): AddedListDao
}