package com.example.cyrptocurrencyapp.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cyrptocurrencyapp.R
import com.example.cyrptocurrencyapp.databinding.FragmentCoinDetailBinding
import com.example.cyrptocurrencyapp.databinding.FragmentLoginBinding
import com.example.cyrptocurrencyapp.ui.MainViewModel
import com.example.cyrptocurrencyapp.ui.detail.CoinDetailViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    private val activityViewModel by activityViewModels<MainViewModel>()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        initLayout()
        return binding.root
    }

    private fun initLayout() = with(binding) {
        activityViewModel.setToolbarVisibility(false)
        signIn.setOnClickListener {
            signIn()
        }
        register.setOnClickListener {
            val directions = LoginFragmentDirections.navigateToRegister()
            findNavController().navigate(directions)
        }
    }

    private fun signIn() = with(binding) {
        val mail = email.text.toString()
        val password = password.text.toString()

        auth.signInWithEmailAndPassword(mail, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val directions = LoginFragmentDirections.navigateToAllList()
                findNavController().navigate(directions)
            } else {
                Toast.makeText(requireContext(),"Wrong infos",Toast.LENGTH_LONG).show()
            }

        }
    }


}