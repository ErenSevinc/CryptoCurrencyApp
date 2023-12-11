package com.example.cyrptocurrencyapp.data.model

import com.example.example.Description
import com.example.example.MarketData
import com.google.gson.annotations.SerializedName

data class CoinDetailDataModel (
    val id: String,
    val name: String,
    val marketData: MarketData,
    val description: Description,
    var statusUpdates: ArrayList<String>,
    var lastUpdated: String,
    var hashingAlgorithm: String
)