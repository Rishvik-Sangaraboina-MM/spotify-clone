package com.example.data.source

import com.example.data.local.MusicDB
import com.example.data.local.model.SongEntity
import com.example.data.repository.IMusicLocalSource

class MusicLocalSource(private val musicDB: MusicDB) : IMusicLocalSource {
  override suspend fun getLocalSongs(term: String): List<SongEntity> {
    return musicDB.getSongDao().getSongs(term)
  }

  override suspend fun insert(song: SongEntity) {
    musicDB.getSongDao().insertSong(song)
  }

  override suspend fun insert(list: List<SongEntity>) {
    musicDB.getSongDao().insertSongList(list)
  }
}