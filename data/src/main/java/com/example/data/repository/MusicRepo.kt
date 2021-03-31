package com.example.data.repository

import com.example.domain.entity.SongResponse
import com.example.domain.repository.IMusicRepo
import com.example.domain.source.IMusicRemoteSource
import com.example.domain.util.SafeResult
import com.example.domain.util.SafeResult.Failure
import com.example.domain.util.SafeResult.NetworkError
import com.example.domain.util.SafeResult.Success

class MusicRepo(private val musicRemoteSource: IMusicRemoteSource) : IMusicRepo {
  override suspend fun search(term: String): SafeResult<List<SongResponse>> {
    return when (val res = musicRemoteSource.search(term)) {
      is Failure -> res
      NetworkError -> NetworkError
      is Success -> Success(res.data.results)
    }
  }
}