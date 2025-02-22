package ru.pugovishnikova.example.vkvideoplayer.domain.util

import ru.pugovishnikova.example.vkvideoplayer.util.Success

enum class DatabaseSuccess: Success {
    INSERT_SUCCESS,
    DELETE_SUCCESS,
    DELETE_ALL_SUCCESS
}