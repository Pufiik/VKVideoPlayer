package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

sealed interface VideoAction {
    data class OnTrackClick(val track: VideoUi, val navigate: () -> Unit) : VideoAction
    data object OnReloadButtonClick : VideoAction
    data class OnBackClick(val navigate: () -> Unit) : VideoAction
}