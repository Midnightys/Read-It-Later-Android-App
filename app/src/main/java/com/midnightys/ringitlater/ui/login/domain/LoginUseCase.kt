package com.midnightys.ringitlater.ui.login.domain

import com.midnightys.ringitlater.data.UserRepository
import com.midnightys.ringitlater.data.UserRepositoryProvider
import com.midnightys.ringitlater.data.userId
import com.midnightys.status.Success
import com.midnightys.status.statusFlow
import com.midnightys.usecase.NormalUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Kort on 2020/3/8.
 */
class LoginUseCase(private val userRepository: UserRepositoryProvider) :
    NormalUseCase<String, Unit>() {
    override fun execute(parameter: userId): Flow<Unit> = flow<Unit> {
        userRepository.createNewUserDocument(parameter)
    }
}