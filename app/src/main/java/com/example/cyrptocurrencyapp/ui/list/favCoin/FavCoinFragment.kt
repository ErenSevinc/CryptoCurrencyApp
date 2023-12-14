package com.example.cyrptocurrencyapp.ui.list.favCoin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cyrptocurrencyapp.data.model.CoinDataModel
import com.example.cyrptocurrencyapp.databinding.FragmentFavCoinBinding
import com.example.cyrptocurrencyapp.presentation.adapter.CoinListAdapter
import com.example.cyrptocurrencyapp.presentation.adapter.PageType
import com.example.cyrptocurrencyapp.ui.MainViewModel
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
    private val activityViewModel by activityViewModels<MainViewModel>()
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
        coinList = args?.allCoin?.coins ?: emptyList<CoinDataModel>().toMutableList()
        activityViewModel.setToolbarVisibility(true)
        activityViewModel.setBackIconVisibility(true)

        setSearchView()
        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        getFavourites()
    }

    private fun setSearchView() {
        binding.filterLayout.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.favCoins.value
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                val list = viewModel.favCoins.value ?: emptyList<CoinDataModel>().toMutableList()
                newText?.let {
                    if (it.isEmpty()) {
                        adapter.setItems(list)
                    } else if (it.length == 2) {

                        val filteredCoin = list.filter { model ->
                            model.symbol.contains(it, true)
                        }.toMutableList()
                        adapter.setItems(filteredCoin)
                    } else if (it.length > 3) {

                        val filteredCoin = list.filter { model ->
                            model.name.contains(it, true)
                        }.toMutableList()
                        adapter.setItems(filteredCoin)
                    }
                }
                return true
            }
        })
    }

    private fun setupObservers() {
        viewModel.favCoins.observe(viewLifecycleOwner) {
            it?.let {list ->
                binding.rvCoinList.isVisible = true
                binding.filterLayout.isVisible = list.isNotEmpty()
                binding.tvError.isVisible = list.isEmpty()

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