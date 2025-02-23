package ru.pugovishnikova.example.vkvideoplayer.data.mappers.api

import ru.pugovishnikova.example.vkvideoplayer.data.model.api.Video
import ru.pugovishnikova.example.vkvideoplayer.presentation.videoList.VideoUi
import ru.pugovishnikova.example.vkvideoplayer.util.Utils

fun Video.toVideoUi() = VideoUi(
    id = id,
    url = videoUrl.checkUrl(),
    title = title,
    imageUrl = thumbnailUrl,
    author = author,
    duration = duration
)

fun String.checkUrl(): String {
    return if (this.contains(Utils.getHttpString())) return Utils.getHttpsString() + this.substring(
        4
    ) else this
}