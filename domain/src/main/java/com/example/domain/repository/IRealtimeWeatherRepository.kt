package com.example.domain.repository

import com.example.domain.models.RealtimeWeatherDomain
import com.example.domain.models.Resource

interface IRealtimeWeatherRepository {
    suspend fun getRealtimeWeather(
        latitude: Float,
        longitude: Float
    ): Resource<RealtimeWeatherDomain>
}