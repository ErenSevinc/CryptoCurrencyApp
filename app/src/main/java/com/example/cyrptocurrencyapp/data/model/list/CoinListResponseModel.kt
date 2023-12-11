package com.example.cyrptocurrencyapp.data.model.list

import com.example.cyrptocurrencyapp.data.model.CoinDataModel
import com.google.gson.annotations.SerializedName

data class CoinListItem(
    @SerializedName("ath") val ath: Double ?= null,
    @SerializedName("ath_change_percentage") val ath_change_percentage: Double ?= null,
    @SerializedName("ath_date") val ath_date: String ?= null,
    @SerializedName("atl") val atl: Double ?= null,
    @SerializedName("atl_change_percentage") val atl_change_percentage: Double ?= null,
    @SerializedName("atl_date") val atl_date: String ?= null,
    @SerializedName("circulating_supply") val circulating_supply: Double ?= null,
    @SerializedName("current_price") val current_price: Double ?= null,
    @SerializedName("fully_diluted_valuation") val fully_diluted_valuation: Long ?= null,
    @SerializedName("high_24h") val high_24h: Double ?= null,
    @SerializedName("id") val id: String ?= null,
    @SerializedName("image") val image: String ?= null,
    @SerializedName("last_updated") val last_updated: String ?= null,
    @SerializedName("low_24h") val low_24h: Double ?= null,
    @SerializedName("market_cap") val market_cap: Long ?= null,
    @SerializedName("market_cap_change_24h") val market_cap_change_24h: Double ?= null,
    @SerializedName("market_cap_change_percentage_24h") val market_cap_change_percentage_24h: Double ?= null,
    @SerializedName("market_cap_rank") val market_cap_rank: Int,
    @SerializedName("max_supply") val max_supply: Double ?= null,
    @SerializedName("name") val name: String ?= null,
    @SerializedName("price_change_24h") val price_change_24h: Double ?= null,
    @SerializedName("price_change_percentage_24h") val price_change_percentage_24h: Double ?= null,
    @SerializedName("roi") val roi: Roi ?= null,
    @SerializedName("symbol") val symbol: String ?= null,
    @SerializedName("total_supply") val total_supply: Double ?= null,
    @SerializedName("total_volume") val total_volume: Long ?= null
) {
    fun toCoinDataModel(): CoinDataModel {
        return CoinDataModel(
            id = id ?: "",
            symbol = symbol ?: "",
            name = name ?: "",
            image = image ?: "",
            market_cap = market_cap ?: 0.toLong(),
            price = current_price ?: 0.toDouble(),
            price_percentage_change = price_change_percentage_24h ?: 0.toDouble(),
            low_price = low_24h ?: 0.toDouble(),
            high_price =  high_24h ?: 0.toDouble()
        )
    }
}

data class Roi(
    @SerializedName("currency") val currency: String ?= null,
    @SerializedName("percentage") val percentage: Double ?= null,
    @SerializedName("times") val times: Double ?= null
)