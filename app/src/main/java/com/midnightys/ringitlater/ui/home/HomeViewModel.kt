package com.midnightys.ringitlater.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midnightys.ringitlater.data.UserRepositoryProvider
import com.midnightys.ringitlater.ui.home.domain.GetAllArticleUseCase
import com.midnightys.status.mapStatus

class HomeViewModel(
    private val userRepository: UserRepositoryProvider,
    private val getAllArticleUseCase: GetAllArticleUseCase
) : ViewModel() {
    val articleInfoList get() = getAllArticleUseCase(viewModelScope)
    val userTitle get() = userRepository.getUserOnce().mapStatus { it.displayName }
}