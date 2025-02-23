package ru.pugovishnikova.example.vkvideoplayer.data

import ru.pugovishnikova.example.vkvideoplayer.data.local.VideoDao
import ru.pugovishnikova.example.vkvideoplayer.data.model.database.VideoEntity
import ru.pugovishnikova.example.vkvideoplayer.data.remote.ApiService
import ru.pugovishnikova.example.vkvideoplayer.domain.VideoRepository
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DataBaseError
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DatabaseSuccess
import ru.pugovishnikova.example.vkvideoplayer.domain.util.NetworkError
import ru.pugovishnikova.example.vkvideoplayer.domain.util.responseToResult
import ru.pugovishnikova.example.vkvideoplayer.presentation.videoList.VideoUi
import ru.pugovishnikova.example.vkvideoplayer.data.mappers.api.toVideoUi
import ru.pugovishnikova.example.vkvideoplayer.util.Result
import ru.pugovishnikova.example.vkvideoplayer.util.map
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val videoDao: VideoDao
) : VideoRepository {
    override suspend fun getVideos(): Result<List<VideoUi>, NetworkError> = try {
        apiService.getVideos()
    } catch (e: Exception) {
        null
    }.let { responseToResult(it).map { videos -> videos.map { video -> video.toVideoUi() } } }

    override suspend fun insertVideo(videoEntity: VideoEntity): Result<DatabaseSuccess, DataBaseError> {
        try {
            videoDao.insertVideo(videoEntity)
            return Result.Success(DatabaseSuccess.INSERT_SUCCESS)
        } catch (e: Exception) {
            return Result.Error(DataBaseError.DATABASE_ERROR_INSERT)
        }
    }

    override suspend fun deleteVideo(videoEntity: VideoEntity): Result<DatabaseSuccess, DataBaseError> {
        try {
            videoDao.deleteVideo(videoEntity)
            return Result.Success(DatabaseSuccess.DELETE_SUCCESS)
        } catch (e: Exception) {
            return Result.Error(DataBaseError.DATABASE_ERROR_DELETE)
        }
    }

    override suspend fun getVideosFromDatabase(): Result<List<VideoEntity>, DataBaseError> {
        try {
            val videos = videoDao.getAllVideos()
            return Result.Success(videos)
        } catch (e: Exception) {
            return Result.Error(DataBaseError.DATABASE_NO_VIDEOS)
        }
    }

    override suspend fun deleteVideosFromDatabase(): Result<DatabaseSuccess, DataBaseError> {
        try {
            videoDao.deleteAllVideos()
            return Result.Success(DatabaseSuccess.DELETE_ALL_SUCCESS)
        } catch (e: Exception) {
            return Result.Error(DataBaseError.DATABASE_ERROR_DELETE)
        }
    }

    override suspend fun getVideoFromDataBase(id: String): Result<VideoEntity, DataBaseError> {
        try {
            val video = videoDao.getVideoById(id)
            return Result.Success(video)
        } catch (e: Exception) {
            return Result.Error(DataBaseError.DATABASE_NO_VIDEO)
        }
    }
}