package com.example.cyrptocurrencyapp.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cyrptocurrencyapp.databinding.FragmentAllCoinBinding
import com.example.cyrptocurrencyapp.presentation.adapter.CoinListAdapter
import com.example.cyrptocurrencyapp.ui.list.allCoin.AllCoinViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : Fragment() {

    private lateinit var binding: FragmentAllCoinBinding
    private lateinit var adapter: CoinListAdapter
    private val viewModel by viewModels<AllCoinViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCoinBinding.inflate(inflater, container, false)
        viewModel.getAllCoin("1")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.allCoin.observe(viewLifecycleOwner) {
            it?.let { list ->
                adapter = CoinListAdapter()
                adapter.setItems(list)
                binding.rvCoinList.layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL, false
                )
                binding.rvCoinList.adapter = adapter
            }
        }
    }
}