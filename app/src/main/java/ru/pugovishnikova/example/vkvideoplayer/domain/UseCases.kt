package ru.pugovishnikova.example.vkvideoplayer.domain

import ru.pugovishnikova.example.vkvideoplayer.domain.usecases.GetVideosUseCase
import javax.inject.Inject

data class UseCases @Inject constructor(
    val getVideosUseCase: GetVideosUseCase
)
