package ru.pugovishnikova.example.vkvideoplayer.domain.util

import ru.pugovishnikova.example.vkvideoplayer.util.Error

enum class DataBaseError : Error {
    DATABASE_ERROR_INSERT,
    DATABASE_ERROR_DELETE,
    DATABASE_NO_VIDEOS,
    DATABASE_NO_VIDEO
}