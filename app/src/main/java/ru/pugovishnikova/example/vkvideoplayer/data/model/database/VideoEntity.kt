package ru.pugovishnikova.example.vkvideoplayer.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video")
data class VideoEntity(
    @PrimaryKey val id: String,
    val title: String,
    val url: String,
    val imageUrl: String?,
    val author: String,
    val duration: String
)