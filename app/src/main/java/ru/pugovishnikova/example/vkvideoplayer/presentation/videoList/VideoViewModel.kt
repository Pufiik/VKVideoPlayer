package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import ru.pugovishnikova.example.vkvideoplayer.data.mappers.database.toVideoEntity
import ru.pugovishnikova.example.vkvideoplayer.data.mappers.database.toVideoUi
import ru.pugovishnikova.example.vkvideoplayer.domain.UseCases
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DownloadError
import ru.pugovishnikova.example.vkvideoplayer.util.Utils
import ru.pugovishnikova.example.vkvideoplayer.util.onError
import ru.pugovishnikova.example.vkvideoplayer.util.onSuccess
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val useCases: UseCases,
    val player: Player
) : ViewModel() {

    private var videoJob: Job? = null
    private var videoJobDatabase: Job? = null
    private val _state = MutableStateFlow(VideoState())
    private val _events = Channel<VideoListEvent>()
    private var currentTrackIndex: Int = 0

    val events = _events.receiveAsFlow()

    val state = _state
        .onStart {
            getVideos()
            player.prepare()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            VideoState()
        )

    fun onAction(action: VideoAction) {
        when (action) {
            is VideoAction.OnVideoClick -> {
                _state.update {
                    it.copy(
                        selectedVideoUi = action.track
                    )
                }
                action.navigate()
            }

            is VideoAction.OnBackClick -> {
                action.navigate()
                player.stop()
            }
        }
    }

    val reload: (Boolean) -> Unit = { isInternet ->
        if (isInternet) getVideos()
        else getVideosFromDatabase()
    }

    private fun getVideos() {
        _state.update {
            it.copy(
                isLoading = true,
                isError = false
            )
        }
        videoJob?.cancel()
        videoJob = viewModelScope.launch {
            withTimeout(5000L) {
                withContext(Dispatchers.IO) {
                    useCases.getVideosUseCase.invoke().onSuccess { videos ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                videos = videos.map { video ->
                                    video.toVideoUi()
                                }
                            )
                        }
                    }
                        .onError { error ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    isError = true
                                )
                            }
                            _events.send(VideoListEvent.ErrorEventNetwork(error))
                        }

                }
            }
        }
    }

    private fun addVideo(item: MediaItem) {
        player.addMediaItem(item)
    }

    fun playVideo() {
        val mediaItems = state.value.videos.map { MediaItem.fromUri(it.url) }
        player.addMediaItems(mediaItems)
        val video = state.value.selectedVideoUi
        val position = state.value.videos.indexOf(video)
        player.setMediaItems(mediaItems, position, 0)
        player.prepare()
        player.play()
    }

    private fun cacheVideo(video: VideoUi) {
        videoJobDatabase?.cancel()
        videoJobDatabase = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCases.cacheVideoUseCase(video)
            }.onSuccess { success ->
                _events.send(VideoListEvent.SuccessEvent(success))
            }.onError { error ->
                _events.send(VideoListEvent.ErrorEventDatabase(error))
            }
        }
    }

    fun deleteVideo(video: VideoUi) {
        deleteAllVideos()
        videoJobDatabase?.cancel()
        videoJobDatabase = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCases.deleteVideoUseCase(video)
            }.onSuccess { success ->
                _events.send(VideoListEvent.SuccessEvent(success))
            }.onError { error ->
                _events.send(VideoListEvent.ErrorEventDatabase(error))
            }
        }
    }

    private fun getVideosFromDatabase() {
        videoJobDatabase?.cancel()
        videoJobDatabase = viewModelScope.launch {
            _state.update {
                it.copy(
                    isError = false
                )
            }
            withContext(Dispatchers.IO) {
                useCases.getVideosUseCaseFromDatabase()
            }.onSuccess { videos ->
                if (videos.isEmpty()) _events.send(VideoListEvent.ErrorDownloadData(DownloadError.NO_VIDEOS))
                _state.update {
                    it.copy(
                        isLoading = false,
                        videos = videos.map { video -> video.toVideoUi() },
                        isError = false
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true
                    )
                }
                _events.send(VideoListEvent.ErrorEventDatabase(error))
            }
        }
    }

    fun getVideoFromDatabase(id: String) {
        videoJobDatabase?.cancel()
        videoJobDatabase = viewModelScope.launch {
            _state.update {
                it.copy(
                    isError = false
                )
            }
            withContext(Dispatchers.IO) {
                useCases.getVideoFromDatabaseUseCase(id)
            }.onSuccess {
                _events.send(VideoListEvent.ErrorDownloadData(DownloadError.ALREADY_DOWNLOAD))
            }.onError {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true
                    )
                }
                cacheVideo(state.value.selectedVideoUi!!)
//                _events.send(VideoListEvent.ErrorEventDatabase(error))
            }
        }
    }

    private fun deleteAllVideos() {
        videoJobDatabase?.cancel()
        videoJobDatabase = viewModelScope.launch {
            _state.update {
                it.copy(
                    isError = false
                )
            }
            withContext(Dispatchers.IO) {
                useCases.deleteAllVideosUseCase()
                    .onSuccess { success ->
                        _events.send(VideoListEvent.SuccessEvent(success))
                    }.onError { error ->
                        _events.send(VideoListEvent.ErrorEventDatabase(error))
                    }
            }
        }
    }
}



