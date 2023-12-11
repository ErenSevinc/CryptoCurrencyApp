package com.example.cyrptocurrencyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.cyrptocurrencyapp.R
import com.example.cyrptocurrencyapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel.getAllCoin("1")
        setupObserver()
    }

    private fun setupObserver() {
        mainViewModel.allCoin.observe(this) {
            it?.let { list->
                binding.textView.text = list.first().name
            }
        }
    }
}