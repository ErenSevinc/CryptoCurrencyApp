package com.example.cyrptocurrencyapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cyrptocurrencyapp.data.model.CoinDataModel
import com.example.cyrptocurrencyapp.data.model.CoinListDataModel
import com.example.cyrptocurrencyapp.databinding.FragmentCoinListBinding
import com.example.cyrptocurrencyapp.presentation.adapter.CoinListAdapter
import com.example.cyrptocurrencyapp.presentation.adapter.PageType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : Fragment() {

    private lateinit var binding: FragmentCoinListBinding
    private lateinit var adapter: CoinListAdapter
    private val viewModel by viewModels<CoinListViewModel>()
    private lateinit var coinList: MutableList<CoinDataModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCoin("1")
        initLayout()
        setupObservers()

    }

    private fun initLayout() {
        setSearchView()

        binding.buttonFavourite.setOnClickListener {
            val direction = CoinListFragmentDirections.navigateToFavCoin(CoinListDataModel(coinList))
            findNavController().navigate(direction)
        }
    }

    private fun setSearchView() {
        binding.filterLayout.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.getAllCoin("1")
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isEmpty()) {
                        adapter.setItems(coinList)
                    } else if (it.length == 2) {

                        val filteredCoin = coinList.filter { model ->
                            model.symbol.contains(it, true)
                        }.toMutableList()
                        adapter.setItems(filteredCoin)
                    } else if (it.length > 3) {

                        val filteredCoin = coinList.filter { model ->
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
        viewModel.allCoin.observe(viewLifecycleOwner) {
            it?.let {list ->
                adapter = CoinListAdapter(PageType.LIST)
                adapter.setItems(list)
                coinList = it
                binding.rvCoinList.layoutManager = LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL, false)
                binding.rvCoinList.adapter = adapter
            }
        }
    }
}