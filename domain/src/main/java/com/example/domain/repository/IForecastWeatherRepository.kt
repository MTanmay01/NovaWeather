package com.example.domain.repository

import com.example.domain.models.ForecastWeatherDomain
import com.example.domain.models.Resource

interface IForecastWeatherRepository {
    suspend fun getForecastData(
        latitude: Float,
        longitude: Float
    ): Resource<ForecastWeatherDomain>
}