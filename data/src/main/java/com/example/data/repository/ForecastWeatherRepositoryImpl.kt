package com.example.data.repository

import com.example.data.api.WeatherAPI
import com.example.data.mappers.toDomain
import com.example.domain.models.ForecastWeatherDomain
import com.example.domain.models.Resource
import com.example.domain.repository.IForecastWeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ForecastWeatherRepositoryImpl @Inject constructor(
    val api: WeatherAPI
) : IForecastWeatherRepository {
    override suspend fun getForecastData(
        latitude: Float,
        longitude: Float
    ): Resource<ForecastWeatherDomain> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = api.getForecastData(q = "$latitude,$longitude")
            if (response.isSuccessful)
                Resource.Success(response.body()!!.toDomain())
            else
                Resource.Error(response.code())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(Resource.NO_INTERNET)
        }
    }
}