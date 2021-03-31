package com.example.baseapp.ui.home.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseapp.util.ViewState
import com.example.domain.usecase.auth.LogoutUserUseCase
import com.example.domain.util.SafeResult.Success
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountVM @Inject constructor(private val logoutUserUseCase: LogoutUserUseCase) : ViewModel() {

  private val _viewState: MutableLiveData<ViewState> = MutableLiveData()
  val viewState: LiveData<ViewState> = _viewState

  fun logout() = viewModelScope.launch {
    when (logoutUserUseCase.perform()) {
      is Success -> _viewState.value = ViewState.Success
      else -> _viewState.value = ViewState.Error("Error")
    }
  }
}