package com.midnightys.ringitlater.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.midnightys.ringitlater.BuildConfig
import com.midnightys.ringitlater.R
import com.midnightys.ringitlater.databinding.LoginFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoginButton()
    }

    private fun setupLoginButton() {
        binding.loginButton.setOnClickListener { showLoginDialog() }
    }

    val requestCodeLogIn = 999
    private fun showLoginDialog() {
        val providers = listOf(AuthUI.IdpConfig.GoogleBuilder().build())
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(!BuildConfig.DEBUG /* credentials */, true /* hints */)
                .setAvailableProviders(providers)
                .setTheme(R.style.Theme_RingItLater)
                .build()
            , requestCodeLogIn
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == requestCodeLogIn) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                viewModel.login()
                Timber.d("Log in success. user provider id: ${FirebaseAuth.getInstance().currentUser?.uid}")
            } else {
                Timber.w("Google Sign in error code:${response?.error?.errorCode}: ${response?.error?.localizedMessage}")
                Timber.e(response?.error)
            }
        }
    }
}