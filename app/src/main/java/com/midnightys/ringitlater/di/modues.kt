package com.midnightys.ringitlater.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.midnightys.ringitlater.data.UserRepository
import com.midnightys.ringitlater.data.UserRepositoryProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by Kort on 2020/3/7.
 */
val modules = listOf<Module>(

)

val commonModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }

    single<UserRepositoryProvider> { UserRepository(get()) }
}