package com.example.example

import com.google.gson.annotations.SerializedName


data class ConvertedLast(

    @SerializedName("btc") var btc: Double? = null,
    @SerializedName("eth") var eth: Double? = null,
    @SerializedName("usd") var usd: Double? = null

)