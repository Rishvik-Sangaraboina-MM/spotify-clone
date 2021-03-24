package com.example.baseapp.ui.auth.signup

import android.os.Bundle
import com.example.baseapp.R
import com.example.baseapp.databinding.ActivitySignUpBinding
import com.example.baseapp.ui.base.BaseActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpVM>() {

  override fun getViewModelClass(): Class<SignUpVM> = SignUpVM::class.java

  override fun getLayoutId() = R.layout.activity_sign_up

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }
}