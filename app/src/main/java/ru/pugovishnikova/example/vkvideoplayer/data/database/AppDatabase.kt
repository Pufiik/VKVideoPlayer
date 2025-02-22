package ru.pugovishnikova.example.vkvideoplayer.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.pugovishnikova.example.vkvideoplayer.data.local.VideoDao
import ru.pugovishnikova.example.vkvideoplayer.data.model.database.VideoEntity
import ru.pugovishnikova.example.vkvideoplayer.util.Utils


@Database(entities = [VideoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getVideoDao(): VideoDao

    companion object {
        private var DB_INSTANCE: AppDatabase? = null
        fun getAppDBInstance(context: Context): AppDatabase {

            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    name = Utils.getAppDatabaseNameString()
                )
                    .allowMainThreadQueries()
                    .build()
            }

            return DB_INSTANCE!!
        }
    }
}