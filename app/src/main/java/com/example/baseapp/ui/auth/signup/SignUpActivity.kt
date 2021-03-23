package com.example.baseapp.ui.auth.signup

import android.os.Bundle
import com.example.baseapp.R
import com.example.baseapp.databinding.ActivitySignUpBinding
import com.example.baseapp.ui.base.ActivityNavigator
import com.example.baseapp.ui.base.BaseActivity
import com.example.baseapp.ui.home.HomeActivity
import com.example.baseapp.util.ViewState.Error
import com.example.baseapp.util.ViewState.Loading
import com.example.baseapp.util.ViewState.Success
import com.example.domain.entity.SignUpRequest

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpVM>() {

  override fun getViewModelClass(): Class<SignUpVM> = SignUpVM::class.java

  override fun getLayoutId() = R.layout.activity_sign_up

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    addListeners()
    addObservers()
  }

  private fun addListeners() {
    binding.btnSignup.setOnClickListener {
      binding.apply {
        signUp(
          editFirstName.text.toString(), editLastName.text.toString(), editUsername.text.toString(),
          editEmail.text.toString(), editPassword.text.toString()
        )
      }
    }
  }

  private fun addObservers() {
    viewModel.viewState.observe(this) {
      binding.isLoading = it == Loading
      when (it) {
        is Error -> showToast(it.msg)
        Success -> ActivityNavigator.startActivityWithSingleTop(this, HomeActivity::class.java)
      }
    }
  }

  private fun signUp(
    firstName: String,
    lastName: String,
    username: String,
    email: String,
    password: String
  ) {
    viewModel.signUp(
      SignUpRequest(email, firstName, lastName, password, "email", null, null, username)
    )
  }
}