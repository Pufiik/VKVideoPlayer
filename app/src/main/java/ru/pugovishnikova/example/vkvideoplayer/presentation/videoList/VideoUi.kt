package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

import ru.pugovishnikova.example.vkvideoplayer.BuildConfig
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.YouTubeVideo
import ru.pugovishnikova.example.vkvideoplayer.util.Utils

data class VideoUi(
    val id: String,
    val title: String,
    val url: String,
    val imageUrl: String?,
    val author: String,
    val duration: String
)


fun YouTubeVideo.toVideoUi() = VideoUi(
    id = id,
    title = snippet?.title ?: Utils.getUnknownString(),
    url = BuildConfig.DOWNLOAD_URL + id,
    imageUrl = snippet?.thumbnails?.values?.first { it.url != null }?.url,
    author = snippet?.channelTitle ?: Utils.getUnknownString(),
    duration = contentDetails?.duration ?: Utils.getUnknownString()
)