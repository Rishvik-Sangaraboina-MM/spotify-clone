package com.example.baseapp.ui.auth.login

import android.os.Bundle
import com.example.baseapp.R
import com.example.baseapp.databinding.ActivityLoginBinding
import com.example.baseapp.ui.auth.login.ViewState.Error
import com.example.baseapp.ui.auth.login.ViewState.Loading
import com.example.baseapp.ui.auth.login.ViewState.Success
import com.example.baseapp.ui.base.ActivityNavigator
import com.example.baseapp.ui.base.BaseActivity
import com.example.baseapp.ui.home.HomeActivity
import com.example.domain.entity.LoginRequest

class LoginActivity : BaseActivity<ActivityLoginBinding,LoginVM>() {

  override fun getViewModelClass(): Class<LoginVM> = LoginVM::class.java

  override fun getLayoutId(): Int = R.layout.activity_login

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.checkUserLoggedIn()
    addListeners()
    addObservers()
  }

  private fun addListeners(){
    binding.btnLogin.setOnClickListener {
      val email = binding.editEmail.text.toString()
      val password = binding.editPassword.text.toString()
      login(email, password)
    }
  }

  private fun addObservers(){
    viewModel.viewState.observe(this){
      binding.isLoading = it==Loading
      when(it){
        is Error -> showToast(it.msg)
        Success -> ActivityNavigator.startActivityWithSingleTop(this,HomeActivity::class.java)
      }
    }
  }

  private fun login(email : String, password : String){

    viewModel.login(LoginRequest("rrrishvik@gmail.com","email","cmHunk@789",null))
  }

}