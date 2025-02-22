package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pugovishnikova.example.vkvideoplayer.util.Utils


@Composable
fun VideoListScreen(
    state: VideoState,
    onAction: (VideoAction) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VideoViewModel
) {

    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (!state.isError) {
        TrackLazyList(state.videos, onAction, onClick, modifier)
    } else {
        ReloadScreen(onClick = viewModel.reload)
    }
}

@Composable
fun TrackLazyList(
    items: List<VideoUi>,
    onAction: (VideoAction) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { videoUi ->
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


@Composable
fun ReloadScreen(
    onClick: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { onClick(true) }) {
            Text(text = Utils.getReloadString())
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button({ onClick(false) }) {
            Text(text = Utils.getDownloadString())
        }
    }
}

@Preview
@Composable
fun show() {
    ReloadScreen { }
}



