package ru.pugovishnikova.example.vkvideoplayer.di

import android.app.Application
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.pugovishnikova.example.vkvideoplayer.data.VideoRepositoryImpl
import ru.pugovishnikova.example.vkvideoplayer.data.local.VideoDao
import ru.pugovishnikova.example.vkvideoplayer.data.remote.ApiService
import ru.pugovishnikova.example.vkvideoplayer.domain.VideoRepository

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun providesVideoRepository(apiService: ApiService, videoDao: VideoDao): VideoRepository {
        return VideoRepositoryImpl(apiService, videoDao)
    }

    @Provides
    @ViewModelScoped
    fun provideVideoPlayer(app: Application): Player {
        return ExoPlayer.Builder(app)
            .build()
    }
}

