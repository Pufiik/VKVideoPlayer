package ru.pugovishnikova.example.vkvideoplayer.presentation.util

import android.content.Context
import ru.pugovishnikova.example.vkvideoplayer.R
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DataBaseError
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DownloadError
import ru.pugovishnikova.example.vkvideoplayer.domain.util.NetworkError

fun NetworkError.toString(context: Context): String {
    val resId = when (this) {
        NetworkError.SERIALIZATION -> R.string.serialization_error
        NetworkError.NO_INTERNET -> R.string.no_internet
        NetworkError.NO_FETCHING_DATA -> R.string.no_fetching_data
        NetworkError.LONG_REQUEST -> R.string.too_long_request
    }
    return context.getString(resId)
}

fun DataBaseError.toString(context: Context): String {
    val resId = when (this) {
        DataBaseError.DATABASE_ERROR_INSERT -> R.string.database_error_insert
        DataBaseError.DATABASE_ERROR_DELETE -> R.string.database_error_delete
        DataBaseError.DATABASE_NO_VIDEOS -> R.string.no_fetching_data
        DataBaseError.DATABASE_NO_VIDEO -> R.string.no_fetching_video
    }
    return context.getString(resId)
}

fun DownloadError.toString(context: Context): String {
    val resId = when (this) {
        DownloadError.ALREADY_DOWNLOAD -> R.string.already_download
        DownloadError.NO_VIDEOS -> R.string.no_videos
    }
    return context.getString(resId)
}