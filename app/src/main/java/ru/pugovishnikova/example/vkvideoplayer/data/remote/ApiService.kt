package ru.pugovishnikova.example.vkvideoplayer.data.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.Video

interface ApiService {


    @GET("videos.json")
    suspend fun getVideos(): Response<List<Video>>
}