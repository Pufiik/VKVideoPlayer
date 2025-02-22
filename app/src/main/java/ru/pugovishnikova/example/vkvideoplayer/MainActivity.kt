package ru.pugovishnikova.example.vkvideoplayer

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import ru.pugovishnikova.example.vkvideoplayer.presentation.util.ObserveAsEvents
import ru.pugovishnikova.example.vkvideoplayer.presentation.util.toString
import ru.pugovishnikova.example.vkvideoplayer.presentation.videoList.VideoDetailScreen
import ru.pugovishnikova.example.vkvideoplayer.presentation.videoList.VideoListEvent
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
                val navController = rememberNavController()
                val viewModel = hiltViewModel<VideoViewModel>()
                val context = LocalContext.current
                val state = viewModel.state.collectAsStateWithLifecycle()
                ObserveAsEvents(events = viewModel.events) { event ->
                    when (event) {
                        is VideoListEvent.ErrorEventNetwork -> {
                            Toast.makeText(
                                context,
                                event.error.toString(context),
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        is VideoListEvent.ErrorEventDatabase -> {
                            Toast.makeText(
                                context,
                                event.error.toString(context),
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        is VideoListEvent.SuccessEvent -> {
                            Toast.makeText(
                                context,
                                event.message.toString(context),
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        is VideoListEvent.ErrorDownloadData -> {
                            Toast.makeText(
                                context,
                                event.message.toString(context),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
                NavHost(
                    navController = navController,
                    startDestination = VideoListScreen
                ) {
                    composable<VideoListScreen> {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            VideoListScreen(
                                state = state.value,
                                onAction = { action ->
                                    viewModel.onAction(action)
                                },
                                onClick = { navController.navigate(VideoDetailScreen) },
                                modifier = Modifier.padding(innerPadding),
                                viewModel = viewModel
                            )
                        }
                    }
                    composable<VideoDetailScreen> {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            VideoDetailScreen(
                                state = state.value,
                                viewModel = viewModel,
                                modifier = Modifier.padding(innerPadding),
                                onBackClick = navController::popBackStack
                            )
                        }
                    }
                }
            }
        }
    }
}


@Serializable
object VideoDetailScreen

@Serializable
object VideoListScreen