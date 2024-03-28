package com.example.data.models.common

import com.google.gson.annotations.SerializedName

data class CurrentData(
    val condition: ConditionData,
    val humidity: Int,
    @SerializedName("air_quality") val airQuality: AirQualityData,
    @SerializedName("temp_c") val tempCelsius: Float,
    @SerializedName("temp_f") val tempFahrenheit: Float,
    @SerializedName("feelslike_c") val feelsLikeCelsius: Float,
    @SerializedName("feelslike_f") val feelsLikeFahrenheit: Float,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("wind_kph") val windKph: Float,
    @SerializedName("wind_mph") val windMph: Float,
)