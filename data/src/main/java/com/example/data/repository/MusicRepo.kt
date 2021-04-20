package com.example.data.repository

import com.example.data.local.model.SongEntity
import com.example.data.mapper.toSong
import com.example.data.mapper.toSongEntity
import com.example.data.remote.model.music.MusicResponse
import com.example.domain.entity.Song
import com.example.domain.repository.IMusicRepo
import com.example.domain.util.SafeResult
import com.example.domain.util.SafeResult.Failure
import com.example.domain.util.SafeResult.NetworkError
import com.example.domain.util.SafeResult.Success

class MusicRepo(
  private val musicRemoteSource: IMusicRemoteSource,
  private val musicLocalSource: IMusicLocalSource
) : IMusicRepo {
  override suspend fun search(term: String): SafeResult<List<Song>> {
    return when (val res = musicRemoteSource.search(term)) {
      is Failure -> res
      is NetworkError -> NetworkError(musicLocalSource.getLocalSongs(term).map { it.toSong() })
      is Success -> {
        musicLocalSource.insert(res.data.results.map { it.toSongEntity(term) })
        Success(res.data.results.map { it.toSong() })
      }
    }
  }
}

interface IMusicRemoteSource {
  suspend fun search(term: String): SafeResult<MusicResponse>
}

interface IMusicLocalSource {
  suspend fun getLocalSongs(term: String): List<SongEntity>
  suspend fun insert(song: SongEntity)
  suspend fun insert(list: List<SongEntity>)
}