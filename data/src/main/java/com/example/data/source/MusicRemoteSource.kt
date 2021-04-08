package com.example.data.source

import com.example.data.mapper.toMusic
import com.example.data.remote.api.MusicApi
import com.example.data.util.safeApiCall
import com.example.domain.entity.Music
import com.example.domain.source.IMusicRemoteSource
import com.example.domain.util.SafeResult

class MusicRemoteSource(private val musicApi: MusicApi) : IMusicRemoteSource {
  override suspend fun search(term: String): SafeResult<Music> {
    return safeApiCall { musicApi.search(term).toMusic() }
  }
}