package com.example.example

import com.google.gson.annotations.SerializedName


data class MarketData(

    @SerializedName("current_price") var currentPrice: CurrentPrice? = CurrentPrice(),
    @SerializedName("total_value_locked") var totalValueLocked: String? = null,
    @SerializedName("mcap_to_tvl_ratio") var mcapToTvlRatio: String? = null,
    @SerializedName("fdv_to_tvl_ratio") var fdvToTvlRatio: String? = null,
    @SerializedName("ath") var ath: Ath? = Ath(),
    @SerializedName("ath_change_percentage") var athChangePercentage: AthChangePercentage? = AthChangePercentage(),
    @SerializedName("ath_date") var athDate: AthDate? = AthDate(),
    @SerializedName("atl") var atl: Atl? = Atl(),
    @SerializedName("atl_change_percentage") var atlChangePercentage: AtlChangePercentage? = AtlChangePercentage(),
    @SerializedName("atl_date") var atlDate: AtlDate? = AtlDate(),
    @SerializedName("market_cap") var marketCap: MarketCap? = MarketCap(),
    @SerializedName("market_cap_rank") var marketCapRank: Double? = null,
    @SerializedName("fully_diluted_valuation") var fullyDilutedValuation: FullyDilutedValuation? = FullyDilutedValuation(),
    @SerializedName("market_cap_fdv_ratio") var marketCapFdvRatio: Double? = null,
    @SerializedName("total_volume") var totalVolume: TotalVolume? = TotalVolume(),
    @SerializedName("high_24h") var high24h: High24h? = High24h(),
    @SerializedName("low_24h") var low24h: Low24h? = Low24h(),
    @SerializedName("price_change_24h") var priceChange24h: Double? = null,
    @SerializedName("price_change_percentage_24h") var priceChangePercentage24h: Double? = null,
    @SerializedName("price_change_percentage_7d") var priceChangePercentage7d: Double? = null,
    @SerializedName("price_change_percentage_14d") var priceChangePercentage14d: Double? = null,
    @SerializedName("price_change_percentage_30d") var priceChangePercentage30d: Double? = null,
    @SerializedName("price_change_percentage_60d") var priceChangePercentage60d: Double? = null,
    @SerializedName("price_change_percentage_200d") var priceChangePercentage200d: Double? = null,
    @SerializedName("price_change_percentage_1y") var priceChangePercentage1y: Double? = null,
    @SerializedName("market_cap_change_24h") var marketCapChange24h: Double? = null,
    @SerializedName("market_cap_change_percentage_24h") var marketCapChangePercentage24h: Double? = null,
    @SerializedName("price_change_24h_in_currency") var priceChange24hInCurrency: PriceChange24hInCurrency? = PriceChange24hInCurrency(),
    @SerializedName("price_change_percentage_1h_in_currency") var priceChangePercentage1hInCurrency: PriceChangePercentage1hInCurrency? = PriceChangePercentage1hInCurrency(),
    @SerializedName("price_change_percentage_24h_in_currency") var priceChangePercentage24hInCurrency: PriceChangePercentage24hInCurrency? = PriceChangePercentage24hInCurrency(),
    @SerializedName("price_change_percentage_7d_in_currency") var priceChangePercentage7dInCurrency: PriceChangePercentage7dInCurrency? = PriceChangePercentage7dInCurrency(),
    @SerializedName("price_change_percentage_14d_in_currency") var priceChangePercentage14dInCurrency: PriceChangePercentage14dInCurrency? = PriceChangePercentage14dInCurrency(),
    @SerializedName("price_change_percentage_30d_in_currency") var priceChangePercentage30dInCurrency: PriceChangePercentage30dInCurrency? = PriceChangePercentage30dInCurrency(),
    @SerializedName("price_change_percentage_60d_in_currency") var priceChangePercentage60dInCurrency: PriceChangePercentage60dInCurrency? = PriceChangePercentage60dInCurrency(),
    @SerializedName("price_change_percentage_200d_in_currency") var priceChangePercentage200dInCurrency: PriceChangePercentage200dInCurrency? = PriceChangePercentage200dInCurrency(),
    @SerializedName("price_change_percentage_1y_in_currency") var priceChangePercentage1yInCurrency: PriceChangePercentage1yInCurrency? = PriceChangePercentage1yInCurrency(),
    @SerializedName("market_cap_change_24h_in_currency") var marketCapChange24hInCurrency: MarketCapChange24hInCurrency? = MarketCapChange24hInCurrency(),
    @SerializedName("market_cap_change_percentage_24h_in_currency") var marketCapChangePercentage24hInCurrency: MarketCapChangePercentage24hInCurrency? = MarketCapChangePercentage24hInCurrency(),
    @SerializedName("total_supply") var totalSupply: Double? = null,
    @SerializedName("max_supply") var maxSupply: Double? = null,
    @SerializedName("circulating_supply") var circulatingSupply: Double? = null,
    @SerializedName("last_updated") var lastUpdated: String? = null

)