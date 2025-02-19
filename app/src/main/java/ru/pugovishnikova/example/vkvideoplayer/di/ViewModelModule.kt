package ru.pugovishnikova.example.vkvideoplayer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.pugovishnikova.example.vkvideoplayer.data.VideoRepositoryImpl
import ru.pugovishnikova.example.vkvideoplayer.data.remote.ApiService
import ru.pugovishnikova.example.vkvideoplayer.domain.VideoRepository

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun providesVideoRepository(apiService: ApiService): VideoRepository {
        return VideoRepositoryImpl(apiService)
    }
}

