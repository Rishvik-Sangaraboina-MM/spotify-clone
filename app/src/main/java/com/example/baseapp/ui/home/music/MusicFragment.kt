package com.example.baseapp.ui.home.music

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import com.example.baseapp.R
import com.example.baseapp.databinding.FragmentMusicBinding
import com.example.baseapp.ui.base.BaseFragment
import com.example.baseapp.util.AppConstants

class MusicFragment : BaseFragment<FragmentMusicBinding, MusicVM>() {

  override fun getViewModelClass(): Class<MusicVM> = MusicVM::class.java

  override fun getLayoutId(): Int = R.layout.fragment_music

  private var chillPlayListAdapter = MusicRecyclerAdapter(AppConstants.PLAYLIST_CHILL)
  private var energyPlayListAdapter = MusicRecyclerAdapter(AppConstants.PLAYLIST_ENERGY)
  private var partyPlayListAdapter = MusicRecyclerAdapter(AppConstants.PLAYLIST_PARTY)
  private var sleepPlayListAdapter = MusicRecyclerAdapter(AppConstants.PLAYLIST_SLEEP)
  private var workoutPlayListAdapter = MusicRecyclerAdapter(AppConstants.PLAYLIST_WORKOUT)

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    addObservers()
    addAdapters()
  }

  private fun addAdapters() {
    binding.musicRecyclerView.adapter = ConcatAdapter()
    with(binding.musicRecyclerView.adapter as ConcatAdapter) {
      addAdapter(chillPlayListAdapter)
      addAdapter(energyPlayListAdapter)
      addAdapter(partyPlayListAdapter)
      addAdapter(sleepPlayListAdapter)
      addAdapter(workoutPlayListAdapter)
    }
  }

  private fun addObservers() {
    with(viewModel) {
      chillLiveData.observe(viewLifecycleOwner) {
        chillPlayListAdapter.addResponse(it)
      }
      energyLiveData.observe(viewLifecycleOwner) {
        energyPlayListAdapter.addResponse(it)
      }
      partyLiveData.observe(viewLifecycleOwner) {
        partyPlayListAdapter.addResponse(it)
      }
      sleepLiveData.observe(viewLifecycleOwner) {
        sleepPlayListAdapter.addResponse(it)
      }
      workoutLiveData.observe(viewLifecycleOwner) {
        workoutPlayListAdapter.addResponse(it)
      }
    }
  }
}