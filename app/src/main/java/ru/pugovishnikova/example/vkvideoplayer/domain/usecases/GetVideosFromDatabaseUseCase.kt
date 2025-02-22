package ru.pugovishnikova.example.vkvideoplayer.domain.usecases

import ru.pugovishnikova.example.vkvideoplayer.data.model.database.VideoEntity
import ru.pugovishnikova.example.vkvideoplayer.domain.VideoRepository
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DataBaseError
import ru.pugovishnikova.example.vkvideoplayer.util.Result
import javax.inject.Inject

class GetVideosFromDatabaseUseCase @Inject constructor(
    private val repository: VideoRepository
) : suspend () -> Result<List<VideoEntity>, DataBaseError> {
    override suspend fun invoke(): Result<List<VideoEntity>, DataBaseError> {
        return repository.getVideosFromDatabase()
    }
}