package com.midnightys.ringitlater.di

import com.midnightys.ringitlater.ui.home.homeModules
import com.midnightys.ringitlater.ui.login.di.loginModule
import com.midnightys.ringitlater.ui.receive.receiveModule
import com.midnightys.ringitlater.ui.slapsh.splashModule

/**
 * Created by Kort on 2020/3/7.
 */
val modules = listOf(
    commonModule,
    splashModule,
    loginModule,
    homeModules,
    receiveModule
)