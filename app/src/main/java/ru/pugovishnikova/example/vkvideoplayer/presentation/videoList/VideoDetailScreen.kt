package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

import android.content.res.Configuration
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import ru.pugovishnikova.example.vkvideoplayer.R
import ru.pugovishnikova.example.vkvideoplayer.util.Utils


@OptIn(UnstableApi::class)
@Composable
fun VideoDetailScreen(
    state: VideoState,
    viewModel: VideoViewModel,
    modifier: Modifier,
    onBackClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    var playbackPosition by rememberSaveable { mutableLongStateOf(0L) }
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    LaunchedEffect(Unit) {
        viewModel.player.seekTo(playbackPosition)
        viewModel.player.playWhenReady = true
    }

    DisposableEffect(Unit) {
        onDispose {
            playbackPosition = viewModel.player.currentPosition
            viewModel.player.pause()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(1.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { viewModel.onAction(VideoAction.OnBackClick(onBackClick)) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = R.string.push_back.toString(),
                    tint = Color.Black
                )
            }
        }
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    this.player = viewModel.player
                    viewModel.playVideo()
                }
            },
//            update = {
//                if (state.isOnPause) {
//                    it.onPause()
//                    it.player?.pause()
//                } else {
//                    it.onResume()
//                }
//            },
            modifier = Modifier
                .fillMaxWidth()
                .then(if (isLandscape) Modifier.fillMaxHeight() else Modifier.aspectRatio(16 / 9f))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.getVideoFromDatabase(state.selectedVideoUi?.id!!) }) {
            Text(Utils.getDownloadTrackString())
        }
        Button(onClick = { viewModel.deleteVideo(state.selectedVideoUi!!) }) {
            Text(Utils.getDeleteTrackString())
        }
    }
}

