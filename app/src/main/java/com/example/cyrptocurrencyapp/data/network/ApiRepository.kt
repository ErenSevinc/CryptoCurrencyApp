package com.example.cyrptocurrencyapp.data.network


import com.example.cyrptocurrencyapp.data.model.detail.CoinDetailResponseModel
import com.example.cyrptocurrencyapp.data.model.list.CoinListItem
import javax.inject.Inject

interface ApiRepository {
     suspend fun getAllCoin(page :String?) : MutableList<CoinListItem>
     suspend fun getSelectedCoin(id: String): CoinDetailResponseModel
}