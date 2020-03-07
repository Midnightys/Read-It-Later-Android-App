package com.midnightys.ringitlater.ui.login.di

import com.midnightys.ringitlater.ui.login.LoginViewModel
import com.midnightys.ringitlater.ui.login.domain.LoginUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Kort on 2020/3/8.
 */
val loginModule = module {
    factory { LoginUseCase(get()) }

    viewModel { LoginViewModel() }
}