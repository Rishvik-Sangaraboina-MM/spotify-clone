package com.example.domain.repository

import com.example.domain.entity.SongResponse
import com.example.domain.util.SafeResult

interface IMusicRepo {

  suspend fun search(term: String): SafeResult<List<SongResponse>>
}