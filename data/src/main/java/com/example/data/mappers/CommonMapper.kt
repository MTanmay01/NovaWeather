package com.example.data.mappers

import com.example.data.models.common.AirQualityData
import com.example.data.models.common.ConditionData
import com.example.data.models.common.CurrentData
import com.example.data.models.common.LocationData
import com.example.domain.models.common.AirQualityDomain
import com.example.domain.models.common.ConditionDomain
import com.example.domain.models.common.CurrentDomain
import com.example.domain.models.common.LocationDomain

fun CurrentData.toDomain() = CurrentDomain(
    condition.toDomain(),
    humidity,
    airQuality.toDomain(),
    "${tempCelsius.toInt()}\u2103",
    "${tempFahrenheit.toInt()}\u2109",
    "${feelsLikeCelsius.toInt()}\u2103",
    "${feelsLikeFahrenheit.toInt()}\u2109",
    isDay == 1,
    "${windKph.toInt()} kph",
    "${windMph.toInt()} mph"
)

fun LocationData.toDomain() = LocationDomain(name)

fun ConditionData.toDomain() = ConditionDomain(
    text,
    "https://$iconUrl",
    code
)

fun AirQualityData.toDomain() = AirQualityDomain(pm25, epaIndex)