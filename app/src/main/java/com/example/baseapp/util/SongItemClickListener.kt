package com.example.baseapp.util

import com.example.domain.entity.SongResponse

interface SongItemClickListener {
  fun onSongItemClick(
    songs: List<SongResponse>,
    index: Int
  )
}