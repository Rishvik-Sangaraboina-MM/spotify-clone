package com.example.domain.entity

data class MusicResponse(
  val resultCount: Int,
  val results: List<SongResponse>
)