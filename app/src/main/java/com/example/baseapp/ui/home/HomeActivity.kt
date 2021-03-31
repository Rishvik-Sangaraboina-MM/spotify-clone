package com.example.baseapp.ui.home

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.baseapp.R
import com.example.baseapp.databinding.ActivityHomeBinding
import com.example.baseapp.ui.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeVM>() {

  override fun getViewModelClass(): Class<HomeVM> = HomeVM::class.java

  override fun getLayoutId(): Int = R.layout.activity_home

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initUI()
  }

  private fun initUI() {
    binding.apply {
      bottomNavigationView.setupWithNavController(
        findNavController(R.id.nav_host_fragment_container)
      )
    }
  }
}