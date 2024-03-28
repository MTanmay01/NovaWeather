package com.example.domain.models

import com.example.domain.models.common.CurrentDomain
import com.example.domain.models.common.LocationDomain

data class RealtimeWeatherDomain(
    val location: LocationDomain,
    val current: CurrentDomain
)