package com.example.baseapp.util

import com.example.domain.entity.SongResponse

interface OnSongChangeListener {
  fun onSongChange(songResponse: SongResponse?)
}