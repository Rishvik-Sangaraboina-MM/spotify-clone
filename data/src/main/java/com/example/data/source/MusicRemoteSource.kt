package com.example.data.source

import com.example.data.remote.api.MusicApi
import com.example.data.remote.model.music.MusicResponse
import com.example.data.repository.IMusicRemoteSource
import com.example.data.util.safeApiCall
import com.example.domain.util.SafeResult

class MusicRemoteSource(private val musicApi: MusicApi) : IMusicRemoteSource {
  override suspend fun search(term: String): SafeResult<MusicResponse> {
    return safeApiCall { musicApi.search(term) }
  }
}