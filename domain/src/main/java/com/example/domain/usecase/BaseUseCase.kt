package com.example.domain.usecase

interface BaseUseCase<in ExecutableParam, out Result> {
  suspend fun perform(): Result = throw NotImplementedError()

  suspend fun perform(executableParam: ExecutableParam): Result = throw NotImplementedError()
}