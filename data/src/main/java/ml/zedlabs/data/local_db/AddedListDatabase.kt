package ml.zedlabs.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AddedListItem::class], version = 1)
abstract class AddedListDatabase : RoomDatabase() {
    abstract fun addedListDao(): AddedListDao
}