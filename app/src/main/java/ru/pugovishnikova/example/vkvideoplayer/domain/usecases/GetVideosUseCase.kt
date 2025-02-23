package ru.pugovishnikova.example.vkvideoplayer.domain.usecases

import ru.pugovishnikova.example.vkvideoplayer.data.VideoRepositoryImpl
import ru.pugovishnikova.example.vkvideoplayer.domain.util.NetworkError
import ru.pugovishnikova.example.vkvideoplayer.presentation.videoList.VideoUi
import ru.pugovishnikova.example.vkvideoplayer.util.Result
import javax.inject.Inject


class GetVideosUseCase @Inject constructor(
    private val repositoryImpl: VideoRepositoryImpl
) : suspend () -> Result<List<VideoUi>, NetworkError> {
    override suspend fun invoke(): Result<List<VideoUi>, NetworkError> {
        return repositoryImpl.getVideos()
    }
}