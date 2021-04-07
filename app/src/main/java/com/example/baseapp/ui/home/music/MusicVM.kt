package com.example.baseapp.ui.home.music

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.baseapp.ui.base.BaseVM
import com.example.baseapp.util.AppConstants
import com.example.baseapp.util.ViewState
import com.example.baseapp.util.ViewState.Error
import com.example.baseapp.util.ViewState.Loading
import com.example.domain.entity.SongResponse
import com.example.domain.usecase.music.FetchMusicUseCase
import com.example.domain.util.SafeResult.Failure
import com.example.domain.util.SafeResult.NetworkError
import com.example.domain.util.SafeResult.Success
import kotlinx.coroutines.launch
import javax.inject.Inject

class MusicVM @Inject constructor(private val fetchMusicUseCase: FetchMusicUseCase) : BaseVM() {

  private val _chillLiveData: MutableLiveData<List<SongResponse>> = MutableLiveData()
  val chillLiveData: LiveData<List<SongResponse>> = _chillLiveData

  private val _energyLiveData: MutableLiveData<List<SongResponse>> = MutableLiveData()
  val energyLiveData: LiveData<List<SongResponse>> = _energyLiveData

  private val _partyLiveData: MutableLiveData<List<SongResponse>> = MutableLiveData()
  val partyLiveData: LiveData<List<SongResponse>> = _partyLiveData

  private val _sleepLiveData: MutableLiveData<List<SongResponse>> = MutableLiveData()
  val sleepLiveData: LiveData<List<SongResponse>> = _sleepLiveData

  private val _workoutLiveData: MutableLiveData<List<SongResponse>> = MutableLiveData()
  val workoutLiveData: LiveData<List<SongResponse>> = _workoutLiveData

  private val _viewState: MutableLiveData<ViewState> = MutableLiveData()
  val viewState: LiveData<ViewState> = _viewState

  private val map = mapOf<String, MutableLiveData<List<SongResponse>>>(
    Pair(AppConstants.PLAYLIST_CHILL, _chillLiveData),
    Pair(AppConstants.PLAYLIST_ENERGY, _energyLiveData),
    Pair(AppConstants.PLAYLIST_PARTY, _partyLiveData),
    Pair(AppConstants.PLAYLIST_SLEEP, _sleepLiveData),
    Pair(AppConstants.PLAYLIST_WORKOUT, _workoutLiveData),
  )

  init {
    search(AppConstants.PLAYLIST_CHILL)
    search(AppConstants.PLAYLIST_PARTY)
    search(AppConstants.PLAYLIST_ENERGY)
    search(AppConstants.PLAYLIST_WORKOUT)
    search(AppConstants.PLAYLIST_SLEEP)
  }

  fun search(term: String) = viewModelScope.launch {
    _viewState.value = Loading
    when (val res = fetchMusicUseCase.perform(term)) {
      is Failure -> _viewState.value = Error("Api Failure")
      NetworkError -> _viewState.value = Error("Network Failure")
      is Success -> {
        map[term]?.value = res.data
        _viewState.value = ViewState.Success
      }
    }
  }
}