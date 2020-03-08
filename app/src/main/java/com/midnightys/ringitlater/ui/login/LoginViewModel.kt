package com.midnightys.ringitlater.ui.login

import androidx.lifecycle.viewModelScope
import com.midnightys.ringitlater.ui.NavigateViewModel
import com.midnightys.ringitlater.ui.login.domain.LoginUseCase
import com.midnightys.status.Success
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginViewModel(private val loginUseCase: LoginUseCase) : NavigateViewModel() {

    fun login() {
        loginUseCase(Unit).onEach {
            if (it is Success) navigateToHome()
        }.launchIn(viewModelScope)
    }

    private fun navigateToHome() {
//        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
//        navigateTo(action)
    }
}