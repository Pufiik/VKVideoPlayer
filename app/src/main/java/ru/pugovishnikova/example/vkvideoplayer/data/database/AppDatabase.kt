package ru.pugovishnikova.example.vkvideoplayer.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [VideoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getPostDao(): PostDao

    companion object {
        private var DB_INSTANCE: AppDatabase? = null
        fun getAppDBInstance(context: Context): AppDatabase {

            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    name = "APP_DB"
                )   .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

            return DB_INSTANCE!!
        }
    }
}