package com.midnightys.ringitlater.ui.home

import com.midnightys.ringitlater.ui.home.domain.GetAllArticleUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Kort on 2020/3/7.
 */
val homeModules = module {
    single { GetAllArticleUseCase(get()) }

    viewModel { HomeViewModel(get(), get()) }
}