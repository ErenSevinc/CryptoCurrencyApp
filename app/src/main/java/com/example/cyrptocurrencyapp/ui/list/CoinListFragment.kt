package com.example.cyrptocurrencyapp.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cyrptocurrencyapp.R
import com.example.cyrptocurrencyapp.databinding.FragmentCoinListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : Fragment() {

    private lateinit var binding: FragmentCoinListBinding
    private val viewModel by viewModels<CoinListViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinListBinding.inflate(inflater,container, false)
        viewModel.getAllCoin("1")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.allCoin.observe(viewLifecycleOwner) {
            binding.textView.text = it.first().id
        }
    }



}