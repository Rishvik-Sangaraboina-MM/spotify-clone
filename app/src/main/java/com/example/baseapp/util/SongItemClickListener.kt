package com.example.baseapp.util

import com.example.domain.entity.Song

interface SongItemClickListener {
  fun onSongItemClick(
    songs: List<Song>,
    index: Int
  )
}