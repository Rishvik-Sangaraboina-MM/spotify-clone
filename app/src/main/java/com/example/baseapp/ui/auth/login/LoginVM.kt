package com.example.baseapp.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseapp.util.ViewState
import com.example.baseapp.util.ViewState.Error
import com.example.baseapp.util.ViewState.Idle
import com.example.baseapp.util.ViewState.Loading
import com.example.domain.entity.LoginRequest
import com.example.domain.usecase.auth.IsUserLoggedInUseCase
import com.example.domain.usecase.auth.LoginUserUseCase
import com.example.domain.util.SafeResult.Failure
import com.example.domain.util.SafeResult.NetworkError
import com.example.domain.util.SafeResult.Success
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginVM @Inject constructor(
  private val loginUserUseCase: LoginUserUseCase,
  private val isUserLoggedInUseCase: IsUserLoggedInUseCase
) : ViewModel() {

  private val _viewState: MutableLiveData<ViewState> = MutableLiveData()
  val viewState: LiveData<ViewState> = _viewState

  fun login(loginRequest: LoginRequest) = viewModelScope.launch {
    _viewState.value = Loading
    when (val res = loginUserUseCase.perform(loginRequest)) {
      is Failure -> _viewState.value = Error(res.message)
      NetworkError -> _viewState.value = Error("Network Error")
      is Success -> _viewState.value = ViewState.Success
    }
  }

  fun checkUserLoggedIn() {
    _viewState.value = Loading
    viewModelScope.launch {
      val result = isUserLoggedInUseCase.perform()
      if (result) _viewState.value = ViewState.Success
      else _viewState.value = Idle
    }
  }
}