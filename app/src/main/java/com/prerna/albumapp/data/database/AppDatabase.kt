package com.prerna.albumapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prerna.albumapp.data.dao.AlbumsDao
import com.prerna.albumapp.data.model.domain.Album

private const val DATABASE_NAME = "album.db"

@Database(
    entities = [Album::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumsDao

    companion object {
        @Volatile
        var instance: AppDatabase? = null
        private val syncLock = Object()

        operator fun invoke(context: Context) = instance ?: buildDatabase(context)

        private fun buildDatabase(context: Context): AppDatabase {
            return synchronized(syncLock) {
                instance ?:
                Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .build()
                    .also { instance = it }
            }
        }
    }
}