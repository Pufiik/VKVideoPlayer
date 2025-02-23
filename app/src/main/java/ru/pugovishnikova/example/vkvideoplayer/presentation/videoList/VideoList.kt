package ru.pugovishnikova.example.vkvideoplayer.presentation.videoList

import android.content.Context
import android.widget.ImageView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
    Card(shape = RoundedCornerShape(15.dp), elevation = CardDefaults.elevatedCardElevation(10.dp), modifier = Modifier.clip(RoundedCornerShape(15.dp))) {
        Row(
            modifier = modifier
                .clickable(onClick = onClick)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
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
                modifier.weight(3f)
            ) {
                Text(
                    text = video.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                modifier
                    .padding(20.dp)
                    .weight(2f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = video.duration,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
        }

    }
}

@Preview
@Composable
fun ShowItem() {
    VideoList(
        video = VideoUi(
            "1",
            "",
            "title",
            "title",
            "author",
            "2:05"
        ),
        onClick = {},
        modifier = Modifier
    )
}