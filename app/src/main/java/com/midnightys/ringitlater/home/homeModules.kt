package com.midnightys.ringitlater.home

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Kort on 2020/3/7.
 */
val homeModules = module {
    viewModel { HomeViewModel() }
}