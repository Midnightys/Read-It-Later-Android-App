package com.midnightys.ringitlater.ui.slapsh

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Kort on 2020/3/8.
 */
val splashModule = module {
    factory { DetermineLoginOrHomeUseCase(get()) }

    viewModel { SplashViewModel(get()) }
}