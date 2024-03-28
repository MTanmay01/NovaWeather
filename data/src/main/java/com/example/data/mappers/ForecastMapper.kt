package com.example.data.mappers

import com.example.data.models.ForecastWeatherData
import com.example.domain.models.ForecastWeatherDomain

fun ForecastWeatherData.toDomain() = ForecastWeatherDomain(
    forecast.toDomain()
)

fun ForecastWeatherData.ForecastData.toDomain() = ForecastWeatherDomain.ForecastDomain(
    forecastDay.map { it.toDomain() }
)

fun ForecastWeatherData.ForecastData.ForecastDayData.toDomain() = ForecastWeatherDomain.ForecastDomain.ForecastDayDomain(
    date,
    day.toDomain()
)

fun ForecastWeatherData.ForecastData.ForecastDayData.DayData.toDomain() = ForecastWeatherDomain.ForecastDomain.ForecastDayDomain.DayDomain(
    condition.toDomain(),
    avgTempCelsius, avgTempFahrenheit
)