package ru.pugovishnikova.example.vkvideoplayer.domain

import ru.pugovishnikova.example.vkvideoplayer.domain.usecases.CacheVideoUseCase
import ru.pugovishnikova.example.vkvideoplayer.domain.usecases.DeleteAllVideosUseCase
import ru.pugovishnikova.example.vkvideoplayer.domain.usecases.DeleteVideoUseCase
import ru.pugovishnikova.example.vkvideoplayer.domain.usecases.GetVideoFromDatabaseUseCase
import ru.pugovishnikova.example.vkvideoplayer.domain.usecases.GetVideosFromDatabaseUseCase
import ru.pugovishnikova.example.vkvideoplayer.domain.usecases.GetVideosUseCase
import javax.inject.Inject

data class UseCases @Inject constructor(
    val getVideosUseCase: GetVideosUseCase,
    val cacheVideoUseCase: CacheVideoUseCase,
    val deleteVideoUseCase: DeleteVideoUseCase,
    val getVideosUseCaseFromDatabase: GetVideosFromDatabaseUseCase,
    val getVideoFromDatabaseUseCase: GetVideoFromDatabaseUseCase,
    val deleteAllVideosUseCase: DeleteAllVideosUseCase
)
