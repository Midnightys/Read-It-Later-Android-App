package com.midnightys.ringitlater.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.midnightys.common.Event
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

/**
 * Created by Kort on 2020/3/8.
 */
abstract class NavigateViewModel : ViewModel() {
    protected val _navigateTo = BroadcastChannel<Event<NavDirections>>(1)
    val navigateTo:Flow<Event<NavDirections>> get() = _navigateTo.asFlow()

    fun navigateTo(navDirections: NavDirections) {
        _navigateTo.offer(Event(navDirections))
    }

    override fun onCleared() {
        _navigateTo.cancel()
    }
}