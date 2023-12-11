package com.example.cyrptocurrencyapp.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cyrptocurrencyapp.R
import com.example.cyrptocurrencyapp.databinding.FragmentCoinDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private lateinit var binding: FragmentCoinDetailBinding
    private val viewModel by viewModels<CoinDetailViewModel>()
    private val args: CoinDetailFragmentArgs by navArgs()
    // TODO Timer
    var default = 30000L


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

    private fun setupObservers() = with(binding) {
        viewModel.selectedCoin.observe(viewLifecycleOwner) {
            it?.let { item ->
                coinName.text = item.name ?: "Empty Name"
                coinPrice.text = "${item.marketData.currentPrice?.usd ?: "Empty Currency"} $"
                coinImage.let { imageView ->
                    Glide.with(requireContext())
                        .load(item.image.large)
                        .apply(RequestOptions().centerCrop())
                        .into(imageView)
                }
                coinPercentPrice.apply {
                    text = "${item.marketData.priceChangePercentage24h ?: 0.0}"
                    if ((item.marketData.priceChangePercentage24h ?: 0.0) < 0.toDouble()) {
                        setTextColor(requireContext().getColor(R.color.low_red))
                    } else if((item.marketData.priceChangePercentage24h ?: 0.0) == 0.toDouble()) {
                        setTextColor(requireContext().getColor(R.color.white))
                    }
                    else {
                        setTextColor(requireContext().getColor(R.color.high_green))
                    }
                }
                coinDescription.text = Html.fromHtml(item.description.en)
            }
        }

    }

}