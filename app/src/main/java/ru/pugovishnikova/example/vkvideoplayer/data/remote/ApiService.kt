package ru.pugovishnikova.example.vkvideoplayer.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.Video
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.VideoResponseDto

interface ApiService {


    @GET("videos.json")
    suspend fun getVideos(): Response<List<Video>>
//    @GET("videos")
//    suspend fun getPopularVideosSnippet(
//        @Query("part") part: String,
//        @Query("chart") chart: String,
//    ): Response<VideoResponseDto>
//
//    @GET("videos")
//    suspend fun getPopularVideosSnippetNextPage(
//        @Query("part") part: String,
//        @Query("chart") chart: String,
//        @Query("pageToken") pageToken: String,
//    ): Response<VideoResponseDto>
//
//    @GET("videos")
//    suspend fun getPopularVideosContentDetails(
//        @Query("part") part: String,
//        @Query("chart") chart: String,
//    ): Response<VideoResponseDto>
//
//    @GET("videos")
//    suspend fun getPopularVideosContentDetailsNextPage(
//        @Query("part") part: String,
//        @Query("chart") chart: String,
//        @Query("pageToken") pageToken: String,
//    ): Response<VideoResponseDto>
}