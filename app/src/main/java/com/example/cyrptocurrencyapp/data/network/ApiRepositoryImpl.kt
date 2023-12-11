package com.example.cyrptocurrencyapp.data.network

import com.example.cyrptocurrencyapp.data.model.detail.CoinDetailResponseModel
import com.example.cyrptocurrencyapp.data.model.list.CoinListItem
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {

    override suspend fun getAllCoin(page: String?): MutableList<CoinListItem> {
        return apiService.getAllCoin(page = page)
    }

    override suspend fun getSelectedCoin(id: String): CoinDetailResponseModel {
        return apiService.getSelectedCoin(id)
    }
}