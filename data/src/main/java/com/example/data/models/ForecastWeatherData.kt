package com.example.data.models

import com.example.data.models.common.ConditionData
import com.google.gson.annotations.SerializedName

data class ForecastWeatherData(
    val forecast: ForecastData
) {
    data class ForecastData(
        @SerializedName("forecastday") val forecastDay: List<ForecastDayData>
    ) {
        data class ForecastDayData(
            val date: String,
            val day: DayData
        ) {
            data class DayData(
                val condition: ConditionData,
                @SerializedName("avgtemp_c") val avgTempCelsius: Float,
                @SerializedName("avgtemp_f") val avgTempFahrenheit: Float
            )
        }
    }
}