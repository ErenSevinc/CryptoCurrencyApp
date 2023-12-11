package com.example.cyrptocurrencyapp.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cyrptocurrencyapp.databinding.FragmentCoinDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private lateinit var binding: FragmentCoinDetailBinding
    private val viewModel by viewModels<CoinDetailViewModel>()
    private val args: CoinDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinDetailBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.coinId
        viewModel.getSelectedCoin(id)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.selectedCoin.observe(viewLifecycleOwner) {
            it?.let { item ->
                binding.textView.text = item.name
            }
        }

    }

}