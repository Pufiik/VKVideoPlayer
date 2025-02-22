package ru.pugovishnikova.example.vkvideoplayer.domain.usecases

import ru.pugovishnikova.example.vkvideoplayer.domain.VideoRepository
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DataBaseError
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DatabaseSuccess
import ru.pugovishnikova.example.vkvideoplayer.util.Result
import javax.inject.Inject

class DeleteAllVideosUseCase @Inject constructor(
    private val repository: VideoRepository
): suspend () -> Result<DatabaseSuccess, DataBaseError>{
    override suspend fun invoke(): Result<DatabaseSuccess, DataBaseError> {
        return repository.deleteVideosFromDatabase()
    }
}