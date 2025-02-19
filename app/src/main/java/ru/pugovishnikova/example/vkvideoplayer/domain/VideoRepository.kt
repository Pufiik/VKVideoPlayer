package ru.pugovishnikova.example.vkvideoplayer.domain

import ru.pugovishnikova.example.vkvideoplayer.data.model.api.YouTubeVideo
import ru.pugovishnikova.example.vkvideoplayer.domain.util.NetworkError
import ru.pugovishnikova.example.vkvideoplayer.util.Result

interface VideoRepository {
    suspend fun getVideos(part: String, chart: String): Result<List<YouTubeVideo>, NetworkError>
}