package com.example.domain.models

import com.example.domain.models.common.ConditionDomain

data class ForecastWeatherDomain(
    val forecast: ForecastDomain
) {
    data class ForecastDomain(
        val forecastDay: List<ForecastDayDomain>
    ) {
        data class ForecastDayDomain(
            val date: String,
            val day: DayDomain
        ) {
            data class DayDomain(
                val condition: ConditionDomain,
                val avgTempCelsius: Float,
                val avgTempFahrenheit: Float
            )
        }
    }
}