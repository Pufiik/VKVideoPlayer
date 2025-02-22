package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

data class VideoState(
    val isLoading: Boolean = false,
    val videos: List<VideoUi> = emptyList(),
    val selectedVideoUi: VideoUi? = null,
    val isError: Boolean = false,
    val isOnPause: Boolean = false,
)