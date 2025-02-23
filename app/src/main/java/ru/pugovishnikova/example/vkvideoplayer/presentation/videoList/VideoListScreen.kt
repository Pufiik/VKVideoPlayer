package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.pugovishnikova.example.vkvideoplayer.util.Utils


@Composable
fun VideoListScreen(
    state: VideoState,
    onAction: (VideoAction) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VideoViewModel
) {
    if (state.isFirstLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            TrackLazyList(state, onAction, onClick, modifier, viewModel)
            Button(
                onClick = { viewModel.reload(!state.isInDownloadScreen) },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp)
            ) {
                if (state.isInDownloadScreen) Text(text = Utils.getInternetString())
                else Text(text = Utils.getDownloadString())
            }
        }
    }
}


@Composable
fun TrackLazyList(
    state: VideoState,
    onAction: (VideoAction) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VideoViewModel
) {
    val swipeRefreshState = rememberSwipeRefreshState(state.isLoading)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { viewModel.reload(state.isInDownloadScreen) }
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.videos) { videoUi ->
                VideoList(
                    video = videoUi,
                    onClick = { onAction(VideoAction.OnVideoClick(videoUi, onClick)) },
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }
}

