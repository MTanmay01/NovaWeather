package com.example.data.models.common

import com.google.gson.annotations.SerializedName

data class ConditionData(
    val text: String,
    @SerializedName("icon") val iconUrl: String,
    val code: Int
)