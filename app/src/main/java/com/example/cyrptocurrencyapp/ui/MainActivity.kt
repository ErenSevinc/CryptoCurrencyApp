package com.example.cyrptocurrencyapp.ui

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cyrptocurrencyapp.R
import com.example.cyrptocurrencyapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        initToolbar()
        setupObserver()
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))

        navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController
        val toolbarConfig = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, toolbarConfig)
    }

    private fun setupObserver() {
        viewModel.title.observe(this) {
            title = it
        }
        viewModel.isToolbarVisible.observe(this) {
            binding.toolbar.isVisible = it
        }
        viewModel.isToolbarNavIconVisibility.observe(this) {
            if (!it) {
                binding.toolbar.navigationIcon = null
            }
        }
    }
}