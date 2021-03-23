package com.example.baseapp.util

sealed class ViewState{
  object Idle : ViewState()
  object Success : ViewState()
  object Loading : ViewState()
  data class Error(val msg: String) : ViewState()
}
