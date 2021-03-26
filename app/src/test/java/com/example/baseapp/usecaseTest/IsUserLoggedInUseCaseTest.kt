package com.example.baseapp.usecaseTest

import com.example.baseapp.BaseTest
import com.example.baseapp.injection.TestAppComponent

class IsUserLoggedInUseCaseTest : BaseTest() {
  override fun injectIntoDagger(testAppComponent: TestAppComponent) {
    testAppComponent.inject(this)
  }
}