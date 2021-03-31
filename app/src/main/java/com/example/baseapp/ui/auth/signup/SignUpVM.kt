package com.example.baseapp.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseapp.util.ViewState
import com.example.baseapp.util.ViewState.Error
import com.example.baseapp.util.ViewState.Loading
import com.example.domain.entity.SignUpRequest
import com.example.domain.usecase.auth.SignUpUserUseCase
import com.example.domain.util.SafeResult.Failure
import com.example.domain.util.SafeResult.NetworkError
import com.example.domain.util.SafeResult.Success
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpVM @Inject constructor(private val signUpUserUseCase: SignUpUserUseCase) : ViewModel() {

  private val _viewState: MutableLiveData<ViewState> = MutableLiveData()
  val viewState: LiveData<ViewState> = _viewState

  fun signUp(signUpRequest: SignUpRequest) = viewModelScope.launch {
    _viewState.value = Loading
    when (val res = signUpUserUseCase.perform(signUpRequest)) {
      is Failure -> _viewState.value = Error(res.message)
      NetworkError -> _viewState.value = Error("Network Error")
      is Success -> _viewState.value = ViewState.Success
    }

  }
}