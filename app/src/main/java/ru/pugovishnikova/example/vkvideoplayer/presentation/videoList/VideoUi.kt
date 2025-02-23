package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.Video
import ru.pugovishnikova.example.vkvideoplayer.util.Utils

data class VideoUi(
    val id: String,
    val url: String,
    val title: String,
    val imageUrl: String?,
    val author: String,
    val duration: String
)


fun Video.toVideoUi() = VideoUi(
    id = id,
    url = videoUrl.checkUrl(),
    title = title,
    imageUrl = thumbnailUrl,
    author = author,
    duration = duration
)

fun String.checkUrl(): String {
    return if (this.contains(Utils.getHttpString())) return Utils.getHttpsString() + this.substring(4) else this
}