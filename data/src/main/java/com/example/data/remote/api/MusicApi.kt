package com.example.data.remote.api

import com.example.domain.entity.MusicResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {
  @GET("search")
  suspend fun search(
    @Query("term")
    term: String
  ): MusicResponse
}