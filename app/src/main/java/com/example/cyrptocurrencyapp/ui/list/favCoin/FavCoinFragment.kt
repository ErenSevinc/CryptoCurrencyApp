package com.example.cyrptocurrencyapp.ui.list.favCoin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cyrptocurrencyapp.data.model.CoinDataModel
import com.example.cyrptocurrencyapp.databinding.FragmentFavCoinBinding
import com.example.cyrptocurrencyapp.presentation.adapter.CoinListAdapter
import com.example.cyrptocurrencyapp.presentation.adapter.PageType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavCoinFragment : Fragment() {

    private lateinit var binding: FragmentFavCoinBinding
    private lateinit var adapter: CoinListAdapter
    private val viewModel by viewModels<FavCoinViewModel>()
    private val args: FavCoinFragmentArgs by navArgs()
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var email: String
    private lateinit var coinList: MutableList<CoinDataModel>

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
        binding = FragmentFavCoinBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        email = auth.currentUser!!.email!!
        coinList = args.allCoin.coins
        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        getFavourites()
    }

    private fun setupObservers() {
        viewModel.favCoins.observe(viewLifecycleOwner) {
            binding.filterLayout.isVisible = true
            binding.rvCoinList.isVisible = true
            binding.tvError.isVisible = false
            it?.let {list ->
                adapter = CoinListAdapter(PageType.FAV)
                adapter.setItems(list)
                binding.rvCoinList.layoutManager = LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL, false)
                binding.rvCoinList.adapter = adapter
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.loading.isVisible = true
                binding.rvCoinList.isVisible = false
                binding.filterLayout.isVisible = false
                binding.tvError.isVisible = false
            } else {
                binding.loading.isVisible = false
            }

        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            it?.let { message->
                binding.filterLayout.isVisible = false
                binding.tvError.text = message
                binding.tvError.isVisible = true
            } ?: run {
                binding.rvCoinList.isVisible = true
                binding.filterLayout.isVisible = true
            }
        }
    }

    private fun getFavourites() {
        db.collection(email).get().addOnCompleteListener { task->
            if (task.isSuccessful) {
                viewModel.checkFavourite(coinList, task.result)
            } else {

            }
        }
    }
}