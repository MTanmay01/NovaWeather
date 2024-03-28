package com.example.domain.models.common

data class CurrentDomain(
    val condition: ConditionDomain,
    val humidity: Int,
    val airQuality: AirQualityDomain,
    val tempCelsius: String,
    val tempFahrenheit: String,
    val feelsLikeCelsius: String,
    val feelsLikeFahrenheit: String,
    val isDay: Boolean,
    val windKph: String,
    val windMph: String,
)