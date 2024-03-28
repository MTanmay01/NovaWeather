package com.example.data.models

import com.example.data.models.common.CurrentData
import com.example.data.models.common.LocationData

data class RealtimeWeatherData(
    val location: LocationData,
    val current: CurrentData
)