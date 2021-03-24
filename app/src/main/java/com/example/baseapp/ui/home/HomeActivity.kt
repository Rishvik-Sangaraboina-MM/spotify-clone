package com.example.baseapp.ui.home

import android.os.Bundle
import com.example.baseapp.R
import com.example.baseapp.databinding.ActivityHomeBinding
import com.example.baseapp.ui.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeVM>() {

  override fun getViewModelClass(): Class<HomeVM> = HomeVM::class.java

  override fun getLayoutId(): Int = R.layout.activity_home

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }
}