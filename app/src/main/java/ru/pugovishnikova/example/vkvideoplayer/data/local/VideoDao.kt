package ru.pugovishnikova.example.vkvideoplayer.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.pugovishnikova.example.vkvideoplayer.data.model.database.VideoEntity


@Dao
interface VideoDao {

    @Query("SELECT * FROM video")
    suspend fun getAllVideos(): List<VideoEntity>

    @Query("select * from video where id= :id")
    suspend fun getVideoById(id: String): VideoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: VideoEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateVideo(video: VideoEntity)

    @Delete
    suspend fun deleteVideo(video: VideoEntity)

    @Query("DELETE FROM video")
    suspend fun deleteAllVideos()
}