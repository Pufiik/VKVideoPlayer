package ru.pugovishnikova.example.vkvideoplayer.data

import ru.pugovishnikova.example.vkvideoplayer.data.mappers.toYouTubeItem
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.YouTubeVideo
import ru.pugovishnikova.example.vkvideoplayer.data.remote.ApiService
import ru.pugovishnikova.example.vkvideoplayer.domain.VideoRepository
import ru.pugovishnikova.example.vkvideoplayer.domain.util.NetworkError
import ru.pugovishnikova.example.vkvideoplayer.domain.util.responseToResult
import ru.pugovishnikova.example.vkvideoplayer.util.Result
import ru.pugovishnikova.example.vkvideoplayer.util.map
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : VideoRepository {
    override suspend fun getVideos(
        part: String,
        chart: String,
    ): Result<List<YouTubeVideo>, NetworkError> = try {
        apiService.getPopularVideosSnippet(part, chart)
    } catch (e: Exception) {
        null
    }.let { responseToResult(it).map { response -> response.items.map { video -> video.toYouTubeItem() } } }
}