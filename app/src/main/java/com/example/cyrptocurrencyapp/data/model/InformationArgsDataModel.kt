package com.example.cyrptocurrencyapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InformationArgsDataModel (
    val id: String,
    val name: String,
    val currentPrice: Double,
    val priceChangePercentage24h: Double,
) : Parcelable