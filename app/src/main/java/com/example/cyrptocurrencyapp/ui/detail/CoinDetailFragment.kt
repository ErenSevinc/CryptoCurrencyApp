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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cyrptocurrencyapp.R
import com.example.cyrptocurrencyapp.data.model.InformationArgsDataModel
import com.example.cyrptocurrencyapp.databinding.FragmentCoinDetailBinding
import com.example.cyrptocurrencyapp.ui.MainViewModel
import com.example.cyrptocurrencyapp.ui.detail.description.DescriptionFragment
import com.example.cyrptocurrencyapp.ui.detail.info.InformationFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private lateinit var binding: FragmentCoinDetailBinding
    private val viewModel by viewModels<CoinDetailViewModel>()
    private val activityViewModel by activityViewModels<MainViewModel>()
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
        activityViewModel.setToolbarVisibility(true)
        activityViewModel.setBackIconVisibility(true)
        initViewPager()
        initTabLayer()
        initTabSelection(0)
        favouriteButton.setOnClickListener {
            if (viewModel.isFav.value == true) {
                dissFavourite()
            } else {
                addFavourite()
            }
        }
    }

    private fun initViewPager() {
        binding.viewPager.isSaveEnabled = false
        binding.viewPager.adapter = object : FragmentStateAdapter(childFragmentManager,viewLifecycleOwner.lifecycle) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> InformationFragment.newInstance()
                    1 -> DescriptionFragment.newInstance(
                        viewModel.selectedCoin.value?.description?.en ?: ""
                    )
                    else -> error("Invalid Index")
                }
            }
            override fun getItemCount(): Int = 2
        }
    }

    private fun initTabLayer() {
        binding.tabLayout.getTabAt(0)?.select()
        binding.viewPager.currentItem = 0
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "COIN PRICE"
                1 -> "DESCRIPTION"
                else -> error("Invalid Index")
            }
        }.attach()
    }

    private fun initTabSelection(tabIndex: Int) {
        if (tabIndex != 0) {
            lifecycleScope.launch {
                delay(100)
                binding.tabLayout.getTabAt(1)?.select()
                binding.viewPager.currentItem = 1
            }
        }
    }

    private fun setupObservers() = with(binding) {
        viewModel.selectedCoin.observe(viewLifecycleOwner) {
            binding.tvError.isVisible = (it == null)

            it?.let { item ->
                viewPager.isVisible = true
                tabLayout.isVisible = true
                favouriteButton.isVisible = true
                coinImage.isVisible = true

                coinImage.let { imageView ->
                    Glide.with(requireContext())
                        .load(item.image.large)
                        .apply(RequestOptions().centerCrop())
                        .into(imageView)
                }
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.loading.isVisible = it
        }
        viewModel.isFav.observe(viewLifecycleOwner) {
            it?.let { isFav ->
                if (isFav) {
                    favouriteButton.setImageResource(R.drawable.ic_star)
                    favouriteButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.accept_yellow
                        )
                    )
                } else {
                    favouriteButton.setImageResource(R.drawable.ic_star_border)
                    favouriteButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.accept_gray
                        )
                    )
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
                    viewModel.getSelectedCoin(id)
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
                    viewModel.getSelectedCoin(id)
                    getFavourites()
                } else {
                    Log.w("ERROR_DISS", task.exception)
                }
            }
        }
    }
}