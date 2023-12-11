package com.example.example

import com.google.gson.annotations.SerializedName


data class PublicInterestStats(

    @SerializedName("alexa_rank") var alexaRank: Double? = null,
    @SerializedName("bing_matches") var bingMatches: String? = null

)