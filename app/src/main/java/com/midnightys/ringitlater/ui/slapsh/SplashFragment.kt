package com.midnightys.ringitlater.ui.slapsh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.midnightys.common.onEachEvent
import com.midnightys.ringitlater.R
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class SplashFragment : Fragment() {


    companion object {
        fun newInstance() = SplashFragment()
    }

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.determineDirection()
    }

    private fun bindViewModel() {
        viewModel.navigateTo.onEachEvent {
            Timber.d("navigateTo: $it")
            findNavController().navigate(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}