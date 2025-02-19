package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

import ru.pugovishnikova.example.vkvideoplayer.domain.util.NetworkError

sealed interface VideoListEvent {
    data class Error(val error: NetworkError): VideoListEvent
}