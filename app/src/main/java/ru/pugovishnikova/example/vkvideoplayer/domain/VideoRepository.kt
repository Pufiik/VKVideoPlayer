package ru.pugovishnikova.example.vkvideoplayer.domain

import ru.pugovishnikova.example.vkvideoplayer.data.model.database.VideoEntity
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DataBaseError
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DatabaseSuccess
import ru.pugovishnikova.example.vkvideoplayer.domain.util.NetworkError
import ru.pugovishnikova.example.vkvideoplayer.presentation.videoList.VideoUi
import ru.pugovishnikova.example.vkvideoplayer.util.Result

interface VideoRepository {
    suspend fun getVideos(): Result<List<VideoUi>, NetworkError>
    suspend fun insertVideo(videoEntity: VideoEntity): Result<DatabaseSuccess, DataBaseError>
    suspend fun deleteVideo(videoEntity: VideoEntity): Result<DatabaseSuccess, DataBaseError>
    suspend fun getVideosFromDatabase(): Result<List<VideoEntity>, DataBaseError>
    suspend fun deleteVideosFromDatabase(): Result<DatabaseSuccess, DataBaseError>
    suspend fun getVideoFromDataBase(id: String): Result<VideoEntity, DataBaseError>
}