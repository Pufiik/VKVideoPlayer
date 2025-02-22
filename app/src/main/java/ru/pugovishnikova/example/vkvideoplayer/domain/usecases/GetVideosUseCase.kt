package ru.pugovishnikova.example.vkvideoplayer.domain.usecases

import ru.pugovishnikova.example.vkvideoplayer.data.VideoRepositoryImpl
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.Video
import ru.pugovishnikova.example.vkvideoplayer.domain.util.NetworkError
import ru.pugovishnikova.example.vkvideoplayer.util.Result
import javax.inject.Inject


class GetVideosUseCase @Inject constructor(
    private val repositoryImpl: VideoRepositoryImpl
) : suspend () -> Result<List<Video>, NetworkError> {
    override suspend fun invoke(): Result<List<Video>, NetworkError> {
        return repositoryImpl.getVideos()
    }
}