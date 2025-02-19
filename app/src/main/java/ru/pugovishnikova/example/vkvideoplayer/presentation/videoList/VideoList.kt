package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

import android.content.Context
import android.widget.ImageView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import ru.pugovishnikova.example.vkvideoplayer.R


@Composable
fun VideoList(
    video: VideoUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AndroidView(
            factory = { ctx: Context ->
                ImageView(ctx).apply {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }
            },
            update = { imageView ->
                Glide.with(context)
                    .load(video.imageUrl)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.no_image)
                            .error(R.drawable.no_image)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    )
                    .into(imageView)
            },
            modifier = Modifier.size(85.dp, 85.dp)
        )
        Column(
            modifier.weight(1f)
        ) {
            Text(
                text = video.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = video.author,
                fontSize = 14.sp
            )
        }
    }

}