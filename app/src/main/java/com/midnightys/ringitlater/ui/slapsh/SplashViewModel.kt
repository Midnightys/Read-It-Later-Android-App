package com.midnightys.ringitlater.ui.slapsh

import androidx.lifecycle.viewModelScope
import com.midnightys.ringitlater.data.UserRepositoryProvider
import com.midnightys.ringitlater.ui.NavigateViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SplashViewModel(private val determineLoginOrHomeUseCase: DetermineLoginOrHomeUseCase) :
    NavigateViewModel() {

    fun determineDirection() {
//        determineLoginOrHomeUseCase.execute(Unit).onEach {
//            val action = when (it) {
//                LoginOrHome.Login -> SplashFragmentDirections.actionSplashFragmentToLoginFragment()
//                LoginOrHome.Home -> SplashFragmentDirections.actionSplashFragmentToHomeFragment()
//            }
//        }.launchIn(viewModelScope)
    }
}