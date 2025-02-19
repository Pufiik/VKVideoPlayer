package ru.pugovishnikova.example.vkvideoplayer.domain.usecases
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.YouTubeVideo
import ru.pugovishnikova.example.vkvideoplayer.data.VideoRepositoryImpl
import ru.pugovishnikova.example.vkvideoplayer.domain.util.NetworkError
import ru.pugovishnikova.example.vkvideoplayer.util.Result
import javax.inject.Inject


class GetVideosUseCase @Inject constructor(
    private val repositoryImpl: VideoRepositoryImpl
) : suspend (String, String) -> Result<List<YouTubeVideo>, NetworkError> {
    override suspend fun invoke(part: String, chart: String): Result<List<YouTubeVideo>, NetworkError> {
        return repositoryImpl.getVideos(
            part = part,
            chart = chart
        )
    }
}