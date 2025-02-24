package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

import android.content.res.Configuration
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
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import ru.pugovishnikova.example.vkvideoplayer.R
import ru.pugovishnikova.example.vkvideoplayer.util.Utils


@Composable
fun VideoDetailScreen(
    state: VideoState,
    viewModel: VideoViewModel,
    modifier: Modifier,
    onBackClick: () -> Unit,
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
            .then(if (!isLandscape) Modifier.padding(16.dp) else Modifier.padding(0.dp))
    ) {
        if (!isLandscape) {
            ShowButtonBack(Modifier, viewModel, onBackClick)
        }
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    this.player = viewModel.player
                    this.player!!.addListener(
                        object : Player.Listener {
                            override fun onIsPlayingChanged(isPlaying: Boolean) {
                                if (!isPlaying)
                                if (player?.currentMediaItemIndex != state.videoPosition)
                                    player?.currentMediaItemIndex?.let { viewModel.update(it) }
                            }
                        }
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .then(if (isLandscape) Modifier.fillMaxHeight() else Modifier.aspectRatio(16 / 9f))
        )

        if (!isLandscape) {
            ShowActions(viewModel, state)
        }
    }
}

@Composable
fun ShowActions(
    viewModel: VideoViewModel,
    state: VideoState
) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth()){
            Text(
                text = state.selectedVideoUi?.title!!,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = state.selectedVideoUi.author,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { viewModel.tryCacheVideoFromDatabase(state.selectedVideoUi?.id!!) }) {
            Icon(
                imageVector = Icons.Filled.Download,
                contentDescription = Utils.getDownloadTrackString(),
                tint = Color.Black
            )
        }
        IconButton(
            onClick = { viewModel.tryDeleteVideoFromDatabase(state.selectedVideoUi?.id!!) },
            modifier = Modifier.size(30.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = Utils.getDownloadTrackString(),
                tint = Color.Black,
            )
        }
    }
}

@Composable
fun ShowButtonBack(modifier: Modifier, viewModel: VideoViewModel, onBackClick: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(1.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        IconButton(onClick = { viewModel.onAction(VideoAction.OnBackClick(onBackClick)) }) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = R.string.push_back.toString(),
                tint = Color.Black
            )
        }
    }
}

