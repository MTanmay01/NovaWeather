package com.example.data.models.common

import com.google.gson.annotations.SerializedName

data class AirQualityData(
    @SerializedName("pm2_5") val pm25: Float,
    @SerializedName("us-epa-index") val epaIndex: Int
)
