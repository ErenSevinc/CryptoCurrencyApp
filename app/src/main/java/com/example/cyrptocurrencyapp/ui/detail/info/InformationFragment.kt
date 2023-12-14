package com.example.cyrptocurrencyapp.ui.detail.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.cyrptocurrencyapp.R
import com.example.cyrptocurrencyapp.data.model.CoinDetailDataModel
import com.example.cyrptocurrencyapp.data.model.InformationArgsDataModel
import com.example.cyrptocurrencyapp.databinding.FragmentInformationBinding
import com.example.cyrptocurrencyapp.ui.detail.CoinDetailViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import java.text.DecimalFormat


@AndroidEntryPoint
class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var email: String
    private val viewModel by viewModels<CoinDetailViewModel>( { this.requireParentFragment() })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email = auth.currentUser!!.email!!

        viewModel.selectedCoin.observe(viewLifecycleOwner) { coinModel ->
            coinModel?.let { coin ->
                var favPrice = 0.0
                db.collection(email).get().addOnSuccessListener { res ->
                    res.forEach {
                        if (it["id"] == coin.id) {
                            favPrice = it["price"] as Double
                        }
                    }
                    initLayout(favPrice, coin)
                }
            }
        }
    }


    private fun initLayout(favPrice: Double, coinModel: CoinDetailDataModel) = with(binding) {
        coinName.text = coinModel.name
        coinPrice.text = "${coinModel.marketData.currentPrice?.usd} $"
        coinPercentPrice.apply {
                text = "${coinModel.marketData.priceChangePercentage24h}"
                if ((coinModel.marketData.priceChangePercentage24h ?: 0.0) < 0.toDouble()) {
                    setTextColor(requireContext().getColor(R.color.low_red))
                } else if ((coinModel.marketData.priceChangePercentage24h) == 0.toDouble()) {
                    setTextColor(requireContext().getColor(R.color.white))
                } else {
                    setTextColor(requireContext().getColor(R.color.high_green))
                }
        }
        coinPercentFavPrice.apply {
            if (favPrice != 0.0) {
                isVisible = true

                val df = DecimalFormat("#.####")
                df.roundingMode = RoundingMode.CEILING

                text = "${df.format(favPrice / (coinModel.marketData?.currentPrice?.usd ?: 0.0))} %"
                if ((coinModel.marketData.currentPrice?.usd ?: 0.0) > favPrice) {
                    setTextColor(requireContext().getColor(R.color.low_red))
                } else if ((coinModel.marketData.currentPrice?.usd) == favPrice) {
                    setTextColor(requireContext().getColor(R.color.white))
                } else {
                    setTextColor(requireContext().getColor(R.color.high_green))
                }
            }
        }
    }


    companion object {
        fun newInstance(): InformationFragment {
            return InformationFragment()
        }
    }

}