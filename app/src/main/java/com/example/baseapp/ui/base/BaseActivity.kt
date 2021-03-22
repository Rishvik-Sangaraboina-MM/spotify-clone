package com.example.baseapp.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : DaggerAppCompatActivity() {
  lateinit var binding: B
  lateinit var viewModel: VM

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    bindContentView(getLayoutId())
  }

  private fun bindContentView(layoutId: Int) {
    binding = DataBindingUtil.setContentView(this, layoutId)
    viewModel = ViewModelProvider(this,viewModelFactory).get(getViewModelClass())
    binding.lifecycleOwner = this
  }

  fun showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT)
      .show()
  }

  abstract fun getViewModelClass(): Class<VM>

  abstract fun getLayoutId(): Int
}