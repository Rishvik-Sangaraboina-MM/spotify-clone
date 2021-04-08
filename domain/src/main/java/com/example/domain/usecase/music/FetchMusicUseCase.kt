package com.example.domain.usecase.music

import com.example.domain.entity.Song
import com.example.domain.repository.IMusicRepo
import com.example.domain.usecase.BaseUseCase
import com.example.domain.util.SafeResult

class FetchMusicUseCase(private val musicRepo: IMusicRepo) : BaseUseCase<String, SafeResult<List<Song>>> {
  override suspend fun perform(executableParam: String): SafeResult<List<Song>> {
    return musicRepo.search(executableParam)
  }
}