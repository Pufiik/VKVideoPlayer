package ru.pugovishnikova.example.vkvideoplayer.domain.util

import ru.pugovishnikova.example.vkvideoplayer.util.Error

enum class DownloadError: Error {
    NO_VIDEOS,
    ALREADY_DOWNLOAD
}

