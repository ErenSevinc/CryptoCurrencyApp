package com.example.cyrptocurrencyapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cyrptocurrencyapp.R
import com.example.cyrptocurrencyapp.data.model.CoinDataModel
import com.example.cyrptocurrencyapp.databinding.ItemCoinListBinding
import com.example.cyrptocurrencyapp.ui.list.CoinListFragmentDirections

class CoinListAdapter() : RecyclerView.Adapter<CoinListAdapter.CoinListViewHolder>() {

    private var list: MutableList<CoinDataModel> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newList: MutableList<CoinDataModel>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        val binding =
            ItemCoinListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CoinListViewHolder(private val binding: ItemCoinListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: CoinDataModel) {

            Glide.with(binding.root.context)
                .load(item.image)
                .apply(RequestOptions().centerCrop())
                .into(binding.coinImage)

            binding.coinName.text = item.name
            binding.coinSymbol.text = item.symbol.uppercase()
            binding.coinPrice.text = "$ ${item.price}"
            binding.coinLevelButton.text = "${item.price_percentage_change}"
            if (item.price_percentage_change < 0) {
                binding.coinLevelButton.setBackgroundColor(binding.root.context.getColor(R.color.low_red))
            } else {
                binding.coinLevelButton.setBackgroundColor(binding.root.context.getColor(R.color.high_green))
            }

            binding.root.setOnClickListener {
                val action = CoinListFragmentDirections.navigateToDetail(coinId = item.id)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}