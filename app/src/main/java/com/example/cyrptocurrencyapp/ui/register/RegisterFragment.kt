package com.example.cyrptocurrencyapp.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cyrptocurrencyapp.databinding.FragmentRegisterBinding
import com.example.cyrptocurrencyapp.ui.login.LoginFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        initLayout()
        return binding.root
    }

    private fun initLayout() = with(binding) {
        register.setOnClickListener {
            register()
        }
    }

    private fun register() = with(binding) {
        val name = name.text.toString()
        val mail = email.text.toString()
        val password = password.text.toString()

        auth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(requireContext(),"Register Successful",Toast.LENGTH_LONG).show()
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(),"Error",Toast.LENGTH_LONG).show()
            }
        }
    }


}