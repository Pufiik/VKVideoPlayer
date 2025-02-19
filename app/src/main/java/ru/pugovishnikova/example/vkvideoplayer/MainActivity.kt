package ru.pugovishnikova.example.vkvideoplayer

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import ru.pugovishnikova.example.vkvideoplayer.presentation.util.ObserveAsEvents
import ru.pugovishnikova.example.vkvideoplayer.presentation.videoList.VideoListScreen
import ru.pugovishnikova.example.vkvideoplayer.presentation.videoList.VideoViewModel
import ru.pugovishnikova.example.vkvideoplayer.ui.theme.VKVideoPlayerTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKVideoPlayerTheme {
                val viewModel = hiltViewModel<VideoViewModel>()
                val state = viewModel.state.collectAsStateWithLifecycle()
                val context = LocalContext.current
//                ObserveAsEvents(events = viewModel.events) { event ->
//                    when (event) {
//                        is TrackListEvent.Error -> {
//                            Toast.makeText(
//                                context,
//                                event.error.toString(context),
//                                Toast.LENGTH_LONG
//                            ).show()
//                        }
//                    }
//                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    VideoListScreen(
                        state = state.value,
                        onAction = { action ->
                            viewModel.onAction(action)
                        },
                        onClick = {},
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VKVideoPlayerTheme {
        Greeting("Android")
    }
}