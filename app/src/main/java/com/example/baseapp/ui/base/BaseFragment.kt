package com.example.baseapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel> : DaggerFragment() {

  lateinit var binding: B
  lateinit var viewModel: VM

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
    binding.lifecycleOwner = this
    return binding.root
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
  }

  fun showToast(msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT)
      .show()
  }

  abstract fun getViewModelClass(): Class<VM>

  abstract fun getLayoutId(): Int
}