package com.example.baseapp.ui.home.account

import android.os.Bundle
import android.view.View
import com.example.baseapp.R
import com.example.baseapp.databinding.FragmentAccountBinding
import com.example.baseapp.ui.auth.login.LoginActivity
import com.example.baseapp.ui.base.BaseFragment
import com.example.baseapp.ui.base.openActivityWithSingleTop
import com.example.baseapp.util.ViewState.Success

class AccountFragment : BaseFragment<FragmentAccountBinding, AccountVM>() {
  override fun getViewModelClass(): Class<AccountVM> = AccountVM::class.java

  override fun getLayoutId(): Int = R.layout.fragment_account

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    addListeners()
    addObservers()
  }

  private fun addListeners() {
    binding.btnLogout.setOnClickListener {
      viewModel.logout()
    }
  }

  private fun addObservers() {
    viewModel.viewState.observe(viewLifecycleOwner) {
      when (it) {
        Success -> context?.openActivityWithSingleTop<LoginActivity>()
      }
    }
  }
}