package ru.pugovishnikova.example.vkvideoplayer.domain.util

import ru.pugovishnikova.example.vkvideoplayer.util.Error
enum class NetworkError : Error {
    NO_FETCHING_DATA,
    NO_INTERNET,
    SERIALIZATION,
    LONG_REQUEST
}