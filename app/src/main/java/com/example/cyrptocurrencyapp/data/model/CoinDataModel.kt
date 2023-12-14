package com.example.cyrptocurrencyapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinDataModel(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val market_cap: Double,
    val price: Double,
    val price_percentage_change: Double,
    val low_price: Double,
    val high_price: Double
) : Parcelable

@Parcelize
data class CoinListDataModel(
    val coins: MutableList<CoinDataModel>
) : Parcelable
