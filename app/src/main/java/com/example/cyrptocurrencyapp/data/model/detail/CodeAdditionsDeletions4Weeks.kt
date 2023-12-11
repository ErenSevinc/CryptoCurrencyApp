package com.example.example

import com.google.gson.annotations.SerializedName


data class CodeAdditionsDeletions4Weeks(

    @SerializedName("additions") var additions: Double? = null,
    @SerializedName("deletions") var deletions: Double? = null

)