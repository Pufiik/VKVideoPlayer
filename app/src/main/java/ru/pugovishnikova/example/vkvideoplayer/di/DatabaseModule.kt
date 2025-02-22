package ru.pugovishnikova.example.vkvideoplayer.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.pugovishnikova.example.vkvideoplayer.data.database.AppDatabase
import ru.pugovishnikova.example.vkvideoplayer.data.local.VideoDao


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun getVideoDao(appDatabase: AppDatabase): VideoDao {
        return appDatabase.getVideoDao()
    }

    @Provides
    fun getAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getAppDBInstance(context)
    }
}