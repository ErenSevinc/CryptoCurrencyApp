package com.example.cyrptocurrencyapp.data.model.detail

import com.example.cyrptocurrencyapp.data.model.CoinDetailDataModel
import com.example.example.CommunityData
import com.example.example.Description
import com.example.example.DeveloperData
import com.example.example.Image
import com.example.example.Links
import com.example.example.Localization
import com.example.example.MarketData
import com.example.example.PublicInterestStats
import com.example.example.Tickers
import com.google.gson.annotations.SerializedName

data class CoinDetailResponseModel(

    @SerializedName("id") var id: String? = null,
    @SerializedName("symbol") var symbol: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("web_slug") var webSlug: String? = null,
    @SerializedName("asset_platform_id") var assetPlatformId: String? = null,
    @SerializedName("block_time_in_minutes") var blockTimeInMinutes: Double? = null,
    @SerializedName("hashing_algorithm") var hashingAlgorithm: String? = null,
    @SerializedName("categories") var categories: ArrayList<String> = arrayListOf(),
    @SerializedName("preview_listing") var previewListing: Boolean? = null,
    @SerializedName("public_notice") var publicNotice: String? = null,
    @SerializedName("additional_notices") var additionalNotices: ArrayList<String> = arrayListOf(),
    @SerializedName("localization") var localization: Localization? = Localization(),
    @SerializedName("description") var description: Description? = Description(),
    @SerializedName("links") var links: Links? = Links(),
    @SerializedName("image") var image: Image? = Image(),
    @SerializedName("country_origin") var countryOrigin: String? = null,
    @SerializedName("genesis_date") var genesisDate: String? = null,
    @SerializedName("sentiment_votes_up_percentage") var sentimentVotesUpPercentage: Double? = null,
    @SerializedName("sentiment_votes_down_percentage") var sentimentVotesDownPercentage: Double? = null,
    @SerializedName("watchlist_portfolio_users") var watchlistPortfolioUsers: Double? = null,
    @SerializedName("market_cap_rank") var marketCapRank: Double? = null,
    @SerializedName("coingecko_rank") var coingeckoRank: Double? = null,
    @SerializedName("coingecko_score") var coingeckoScore: Double? = null,
    @SerializedName("developer_score") var developerScore: Double? = null,
    @SerializedName("community_score") var communityScore: Double? = null,
    @SerializedName("liquidity_score") var liquidityScore: Double? = null,
    @SerializedName("public_interest_score") var publicInterestScore: Double? = null,
    @SerializedName("market_data") var marketData: MarketData? = MarketData(),
    @SerializedName("community_data") var communityData: CommunityData? = CommunityData(),
    @SerializedName("developer_data") var developerData: DeveloperData? = DeveloperData(),
    @SerializedName("public_interest_stats") var publicInterestStats: PublicInterestStats? = PublicInterestStats(),
    @SerializedName("status_updates") var statusUpdates: ArrayList<String> = arrayListOf(),
    @SerializedName("last_updated") var lastUpdated: String? = null,
    @SerializedName("tickers") var tickers: ArrayList<Tickers> = arrayListOf()
) {
    fun toCoinDetail(): CoinDetailDataModel {
        return CoinDetailDataModel(
            id = id ?: "",
            name = name ?: "",
            image = image ?: Image(),
            marketData = marketData ?: MarketData(),
            description = description ?: Description(),
            statusUpdates = statusUpdates ?: arrayListOf<String>(),
            lastUpdated = lastUpdated ?: "",
            hashingAlgorithm = hashingAlgorithm ?: ""
        )
    }
}