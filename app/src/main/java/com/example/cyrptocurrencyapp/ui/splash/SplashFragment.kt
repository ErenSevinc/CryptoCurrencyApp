package com.example.cyrptocurrencyapp.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cyrptocurrencyapp.databinding.FragmentSplashBinding
import com.example.cyrptocurrencyapp.ui.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val viewModel by viewModels<SplashViewModel>()
    private val activityViewModel by activityViewModels<MainViewModel>()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkLogin()
    }

    private fun initLayout() {
        activityViewModel.setToolbarVisibility(false)
    }

    private fun checkLogin() {
        lifecycleScope.launch {
            delay(2000L)
            if (auth.currentUser != null) {
                val direction = SplashFragmentDirections.navigateToCoinList()
                findNavController().navigate(direction)
            } else {
                val direction = SplashFragmentDirections.navigateToLogin()
                findNavController().navigate(direction)
            }
        }
    }

}