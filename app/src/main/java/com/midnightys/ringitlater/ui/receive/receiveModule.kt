package com.midnightys.ringitlater.ui.receive

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Kort on 2020/3/8.
 */
val receiveModule = module {
    viewModel { ReceiveArticleViewModel(get()) }
}