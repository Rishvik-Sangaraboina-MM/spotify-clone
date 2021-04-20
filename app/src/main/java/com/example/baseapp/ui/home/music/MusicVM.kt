package com.example.baseapp.ui.home.music

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseapp.util.AppConstants
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

class MusicVM @Inject constructor(private val fetchMusicUseCase: FetchMusicUseCase) : ViewModel() {

  private val _chillLiveData: MutableLiveData<List<Song>> = MutableLiveData()
  val chillLiveData: LiveData<List<Song>> = _chillLiveData

  private val _energyLiveData: MutableLiveData<List<Song>> = MutableLiveData()
  val energyLiveData: LiveData<List<Song>> = _energyLiveData

  private val _partyLiveData: MutableLiveData<List<Song>> = MutableLiveData()
  val partyLiveData: LiveData<List<Song>> = _partyLiveData

  private val _sleepLiveData: MutableLiveData<List<Song>> = MutableLiveData()
  val sleepLiveData: LiveData<List<Song>> = _sleepLiveData

  private val _workoutLiveData: MutableLiveData<List<Song>> = MutableLiveData()
  val workoutLiveData: LiveData<List<Song>> = _workoutLiveData

  private val _viewState: MutableLiveData<ViewState> = MutableLiveData()
  val viewState: LiveData<ViewState> = _viewState

  private val map = mapOf(
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
      is NetworkError -> {
        _viewState.value = Error("Network Failure")
        map[term]?.value = res.cache
      }
      is Success -> {
        map[term]?.value = res.data
        _viewState.value = ViewState.Success
      }
    }
  }
}