package com.example.data.remote.model.music

data class MusicResponse(
  val resultCount: Int,
  val results: List<SongResponse>
)