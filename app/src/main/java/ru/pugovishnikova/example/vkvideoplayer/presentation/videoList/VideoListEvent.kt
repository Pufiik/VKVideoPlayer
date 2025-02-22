package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

import ru.pugovishnikova.example.vkvideoplayer.domain.util.DataBaseError
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DatabaseSuccess
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DownloadError
import ru.pugovishnikova.example.vkvideoplayer.domain.util.NetworkError

sealed interface VideoListEvent {
    data class ErrorEventNetwork(val error: NetworkError) : VideoListEvent
    data class ErrorEventDatabase(val error: DataBaseError) : VideoListEvent
    data class SuccessEvent(val message: DatabaseSuccess) : VideoListEvent
    data class ErrorDownloadData(val message: DownloadError) : VideoListEvent
}