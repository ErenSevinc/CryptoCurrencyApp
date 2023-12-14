package com.example.cyrptocurrencyapp.ui.detail

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cyrptocurrencyapp.R
import com.example.cyrptocurrencyapp.databinding.FragmentCoinDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private lateinit var binding: FragmentCoinDetailBinding
    private val viewModel by viewModels<CoinDetailViewModel>()
    private val args: CoinDetailFragmentArgs by navArgs()

    // TODO Timer
    var default = 30000L
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var email: String
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = args.coinId
        email = auth.currentUser!!.email!!
        viewModel.getSelectedCoin(id)
        getFavourites()
        initLayout()
        setupObservers()
    }

    private fun initLayout() = with(binding) {
        favouriteButton.setOnClickListener {
            if (viewModel.isFav.value == true) {
                dissFavourite()
            } else {
                addFavourite()
            }
        }
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
                    } else if ((item.marketData.priceChangePercentage24h ?: 0.0) == 0.toDouble()) {
                        setTextColor(requireContext().getColor(R.color.white))
                    } else {
                        setTextColor(requireContext().getColor(R.color.high_green))
                    }
                }
                coinDescription.text = Html.fromHtml(item.description.en)
            }
        }
        viewModel.isFav.observe(viewLifecycleOwner) {
            it?.let { isFav ->
                if (isFav) {
                    favouriteButton.setImageResource(R.drawable.ic_star)
                    favouriteButton.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.accept_yellow))
                } else {
                    favouriteButton.setImageResource(R.drawable.ic_star_border)
                    favouriteButton.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.accept_gray))
                }
                favouriteButton.isVisible = true
            } ?: run {
                favouriteButton.isVisible = false
            }
        }
    }

    private fun getFavourites() {
        db.collection(email).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                viewModel.checkFavourite(args.coinId, task.result)
            } else {

            }
        }

    }

    private fun addFavourite() {
        viewModel.selectedCoin.value?.let { item ->
            val coin = hashMapOf(
                "id" to item.id,
                "price" to item.marketData.currentPrice?.usd
            )
            db.collection(email).add(coin).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    getFavourites()
                } else {
                    Log.w("ERROR_ADD", task.exception)
                }
            }
        }
    }

    private fun dissFavourite() {
        viewModel.favDocument.value?.let {
            db.collection(email).document(it.id).delete().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    getFavourites()
                } else {
                    Log.w("ERROR_DISS", task.exception)
                }
            }
        }
    }
}