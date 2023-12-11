package com.example.cyrptocurrencyapp.data.model

data class CoinDataModel(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val market_cap : Double,
    val price: Double,
    val price_percentage_change: Double,
    val low_price: Double,
    val high_price: Double
) {
}
