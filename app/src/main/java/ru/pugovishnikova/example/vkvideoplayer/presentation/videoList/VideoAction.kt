package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

sealed interface VideoAction {
    data class OnVideoClick(val track: VideoUi, val navigate: () -> Unit) : VideoAction
    data class OnBackClick(val navigate: () -> Unit) : VideoAction
}