package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

data class VideoUi(
    val id: String,
    val url: String,
    val title: String,
    val imageUrl: String?,
    val author: String,
    val duration: String
)
