package com.example.baseapp.ui.home.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseapp.util.ViewState
import com.example.baseapp.util.ViewState.Error
import com.example.baseapp.util.ViewState.Loading
import com.example.domain.entity.Song
import com.example.domain.usecase.music.FetchMusicUseCase
import com.example.domain.util.SafeResult.Failure
import com.example.domain.util.SafeResult.NetworkError
import com.example.domain.util.SafeResult.Success
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchVM @Inject constructor(private val fetchMusicUseCase: FetchMusicUseCase) : ViewModel() {
  private val _searchLiveData: MutableLiveData<List<Song>> = MutableLiveData()
  val searchLiveData: LiveData<List<Song>> = _searchLiveData

  private val _viewState: MutableLiveData<ViewState> = MutableLiveData()
  val viewState: LiveData<ViewState> = _viewState

  fun search(term: String) = viewModelScope.launch {
    _viewState.value = Loading
    when (val res = fetchMusicUseCase.perform(term)) {
      is Failure -> _viewState.value = Error("Api Failure")
      NetworkError -> _viewState.value = Error("Network Failure")
      is Success -> {
        _searchLiveData.value = res.data
        _viewState.value = ViewState.Success
      }
    }
  }
}