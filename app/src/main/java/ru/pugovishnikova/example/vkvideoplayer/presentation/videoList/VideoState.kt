package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

data class VideoState(
    val isLoading: Boolean = false,
    val videos: List<VideoUi> = emptyList(),
    val selectedVideoUi: VideoUi? = null,
    val isInDownloadScreen: Boolean = false,
    val videoPosition: Int = -1,
    val isFirstLoading: Boolean = true
)