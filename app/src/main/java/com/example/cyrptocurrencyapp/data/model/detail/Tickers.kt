package com.example.example

import com.google.gson.annotations.SerializedName


data class Tickers(

    @SerializedName("base") var base: String? = null,
    @SerializedName("target") var target: String? = null,
    @SerializedName("market") var market: Market? = Market(),
    @SerializedName("last") var last: Double? = null,
    @SerializedName("volume") var volume: Double? = null,
    @SerializedName("converted_last") var convertedLast: ConvertedLast? = ConvertedLast(),
    @SerializedName("converted_volume") var convertedVolume: ConvertedVolume? = ConvertedVolume(),
    @SerializedName("trust_score") var trustScore: String? = null,
    @SerializedName("bid_ask_spread_percentage") var bidAskSpreadPercentage: Double? = null,
    @SerializedName("timestamp") var timestamp: String? = null,
    @SerializedName("last_traded_at") var lastTradedAt: String? = null,
    @SerializedName("last_fetch_at") var lastFetchAt: String? = null,
    @SerializedName("is_anomaly") var isAnomaly: Boolean? = null,
    @SerializedName("is_stale") var isStale: Boolean? = null,
    @SerializedName("trade_url") var tradeUrl: String? = null,
    @SerializedName("token_info_url") var tokenInfoUrl: String? = null,
    @SerializedName("coin_id") var coinId: String? = null,
    @SerializedName("target_coin_id") var targetCoinId: String? = null

)