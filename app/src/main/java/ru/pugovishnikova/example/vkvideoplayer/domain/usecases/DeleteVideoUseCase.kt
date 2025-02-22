package ru.pugovishnikova.example.vkvideoplayer.domain.usecases

import ru.pugovishnikova.example.vkvideoplayer.data.mappers.database.toVideoEntity
import ru.pugovishnikova.example.vkvideoplayer.domain.VideoRepository
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DataBaseError
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DatabaseSuccess
import ru.pugovishnikova.example.vkvideoplayer.presentation.videoList.VideoUi
import ru.pugovishnikova.example.vkvideoplayer.util.Result
import javax.inject.Inject

class DeleteVideoUseCase @Inject constructor(
    private val repository: VideoRepository
) : suspend (VideoUi) -> Result<DatabaseSuccess, DataBaseError> {
    override suspend fun invoke(video: VideoUi): Result<DatabaseSuccess, DataBaseError> {
        return repository.deleteVideo(video.toVideoEntity())
    }
}