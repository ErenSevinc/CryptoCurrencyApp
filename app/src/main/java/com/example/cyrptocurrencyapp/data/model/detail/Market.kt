package com.example.example

import com.google.gson.annotations.SerializedName


data class Market(

    @SerializedName("name") var name: String? = null,
    @SerializedName("identifier") var identifier: String? = null,
    @SerializedName("has_trading_incentive") var hasTradingIncentive: Boolean? = null

)