package com.example.data.mappers

import com.example.data.models.RealtimeWeatherData
import com.example.domain.models.RealtimeWeatherDomain

fun RealtimeWeatherData.toDomain() = RealtimeWeatherDomain(
    location.toDomain(),
    current.toDomain()
)