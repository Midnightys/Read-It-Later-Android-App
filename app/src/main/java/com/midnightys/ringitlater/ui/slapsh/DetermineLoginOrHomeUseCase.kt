package com.midnightys.ringitlater.ui.slapsh

import com.midnightys.ringitlater.data.UserRepositoryProvider
import com.midnightys.status.Status
import com.midnightys.status.Success
import com.midnightys.status.ignoreLoading
import com.midnightys.status.statusSingle
import com.midnightys.usecase.FlowUseCase
import com.midnightys.usecase.StatusUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

/**
 * Created by Kort on 2020/3/8.
 */
enum class LoginOrHome { Login, Home }

class DetermineLoginOrHomeUseCase(private val userRepository: UserRepositoryProvider) :
    FlowUseCase<Unit, LoginOrHome>() {
    override fun execute(parameter: Unit): Flow<LoginOrHome> =
        userRepository
            .getUserOnce()
            .ignoreLoading()
            .map {
                if (it is Success) LoginOrHome.Home
                else LoginOrHome.Login
            }
}