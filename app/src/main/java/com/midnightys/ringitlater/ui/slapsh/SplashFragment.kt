package com.midnightys.ringitlater.ui.slapsh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.midnightys.ringitlater.R
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.viewmodel.ext.android.viewModel

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
//        viewModel.navigateTo.onEachEvent {
//            findNavController().navigate(it)
//        }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.determineDirection()
    }
}