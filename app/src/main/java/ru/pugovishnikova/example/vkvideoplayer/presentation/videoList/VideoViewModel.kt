package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import ru.pugovishnikova.example.vkvideoplayer.domain.UseCases
import ru.pugovishnikova.example.vkvideoplayer.util.Utils
import ru.pugovishnikova.example.vkvideoplayer.util.onError
import ru.pugovishnikova.example.vkvideoplayer.util.onSuccess
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private var videoJob: Job? = null
    private val _state = MutableStateFlow(VideoState())
    private val _events = Channel<VideoListEvent>()
    val events = _events.receiveAsFlow()

    val state = _state
        .onStart {
            getVideos()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            VideoState()
        )

    fun onAction(action: VideoAction) {
        when (action) {
            is VideoAction.OnTrackClick -> {}
            is VideoAction.OnReloadButtonClick -> {}
            is VideoAction.OnBackClick -> {}
        }
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
                getPopularVideos()
            }
        }
    }

    private suspend fun getPopularVideos(page: String? = null) = withContext(Dispatchers.IO) {
        if (page != null) {

        } else {
            useCases.getVideosUseCase.invoke(
                part = Utils.getSnippetString(),
                chart = Utils.getMostPopularString()
            ).onSuccess { videos ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        videos = videos.map { video ->
                            video.toVideoUi()
                        }
                    )
                }
            }
                .onError {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true
                        )
                    }
                }
        }
    }

}

