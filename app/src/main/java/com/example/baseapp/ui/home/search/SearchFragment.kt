package com.example.baseapp.ui.home.search

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baseapp.R
import com.example.baseapp.databinding.FragmentSearchBinding
import com.example.baseapp.ui.base.BaseFragment
import com.example.baseapp.ui.home.HomeActivity
import com.example.baseapp.ui.home.music.SongsRecyclerAdapter
import com.example.baseapp.util.ViewState.Error
import com.example.baseapp.util.ViewState.Idle
import com.example.baseapp.util.ViewState.Loading
import com.example.baseapp.util.ViewState.Success

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchVM>() {

  override fun getViewModelClass(): Class<SearchVM> = SearchVM::class.java

  override fun getLayoutId(): Int = R.layout.fragment_search

  private val songsRecyclerAdapter = SongsRecyclerAdapter()

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    addObserver()
    addListener()
    initUI()
  }

  private fun addListener() {
    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.search(it) }
        return false
      }

      override fun onQueryTextChange(newText: String?): Boolean = false
    })
  }

  private fun initUI() {
    with(binding) {
      recyclerView.layoutManager = GridLayoutManager(context, 2)
      recyclerView.adapter = songsRecyclerAdapter
    }
    songsRecyclerAdapter.isGridLayout = true
    songsRecyclerAdapter.setOnClickListener(activity as HomeActivity)
  }

  private fun addObserver() {
    viewModel.viewState.observe(viewLifecycleOwner) {
      when (it) {
        is Error -> Unit
        Idle -> Unit
        Loading -> Unit
        is Success -> {
          viewModel.searchLiveData.value?.let { response ->
            (binding.recyclerView.adapter as SongsRecyclerAdapter).addResponse(response)
          }
        }
      }
    }
  }
}