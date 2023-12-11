package com.example.cyrptocurrencyapp.data.network

import com.example.cyrptocurrencyapp.data.model.detail.CoinDetailResponseModel
import com.example.cyrptocurrencyapp.data.model.list.CoinListItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false")
    suspend fun getAllCoin(
        @Query("page") page: String ?= "1"
    ) : MutableList<CoinListItem>

    @GET("/api/v3/coins/{id}")
    suspend fun getSelectedCoin(@Path("id") id:String ): CoinDetailResponseModel
}