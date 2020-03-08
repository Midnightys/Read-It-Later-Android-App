package com.midnightys.ringitlater.ui.login.domain

import com.midnightys.ringitlater.data.UserRepositoryProvider
import com.midnightys.status.Status
import com.midnightys.usecase.StatusUseCase
import com.midnightys.usecase.UseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Kort on 2020/3/8.
 */
class LoginUseCase(private val userRepository: UserRepositoryProvider) :
    StatusUseCase<Unit, Unit>() {

    override fun execute(parameter: Unit): Flow<Status<Unit>> =
        userRepository.createNewUserDocument()
}