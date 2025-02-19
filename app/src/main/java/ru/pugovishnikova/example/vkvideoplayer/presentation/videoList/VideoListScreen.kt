package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import ru.pugovishnikova.example.vkvideoplayer.util.Utils


@Composable
fun VideoListScreen(
    state: VideoState,
    onAction: (VideoAction) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
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
        ReloadScreen { onAction(VideoAction.OnReloadButtonClick) }
    }
}

@Composable
fun TrackLazyList(items: List<VideoUi>,
                  onAction: (VideoAction) -> Unit,
                  onClick: () -> Unit,
                  modifier: Modifier = Modifier,) {
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
                    onClick = { onAction(VideoAction.OnTrackClick(videoUi, onClick)) },
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()

            }
        }
    }
}


@Composable
fun ReloadScreen(onReloadClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onReloadClick) {
            Text(text = Utils.getReloadString())
        }
    }
}
//
//@Composable
//fun SearchInputField(
//    onSearch: (String) -> Unit
//) {
//    var text by remember { mutableStateOf(Utils.getEmptyString()) }
//
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        OutlinedTextField(
//            value = text,
//            onValueChange = { text = it },
//            modifier = Modifier.weight(1f),
//            label = { Text(Utils.getInputText()) },
//            leadingIcon = {
//                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
//            }
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//        Button(onClick = { onSearch(text.lowercase()) }) {
//            Text(Utils.getSearch())
//        }
//    }
//}


