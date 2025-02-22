package ru.pugovishnikova.example.vkvideoplayer.domain.usecases

import ru.pugovishnikova.example.vkvideoplayer.data.model.database.VideoEntity
import ru.pugovishnikova.example.vkvideoplayer.domain.VideoRepository
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DataBaseError
import ru.pugovishnikova.example.vkvideoplayer.util.Result
import javax.inject.Inject

class GetVideoFromDatabaseUseCase @Inject constructor(
    private val repository: VideoRepository
) : suspend (String) -> Result<VideoEntity, DataBaseError> {
    override suspend fun invoke(id: String): Result<VideoEntity, DataBaseError> {
        return repository.getVideoFromDataBase(id)
    }
}