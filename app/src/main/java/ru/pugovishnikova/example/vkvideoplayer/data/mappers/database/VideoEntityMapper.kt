package ru.pugovishnikova.example.vkvideoplayer.data.mappers.database

import ru.pugovishnikova.example.vkvideoplayer.data.model.database.VideoEntity
import ru.pugovishnikova.example.vkvideoplayer.presentation.videoList.VideoUi

fun VideoUi.toVideoEntity() = VideoEntity(
    id = id,
    url = url,
    title = title,
    duration = duration,
    author = author,
    imageUrl = imageUrl
)

fun VideoEntity.toVideoUi() = VideoUi(
    id = id,
    url = url,
    title = title,
    duration = duration,
    author = author,
    imageUrl = imageUrl
)