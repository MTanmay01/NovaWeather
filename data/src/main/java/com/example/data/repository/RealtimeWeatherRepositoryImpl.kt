package com.example.data.repository

import com.example.data.api.WeatherAPI
import com.example.data.mappers.toDomain
import com.example.domain.models.RealtimeWeatherDomain
import com.example.domain.models.Resource
import com.example.domain.repository.IRealtimeWeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RealtimeWeatherRepositoryImpl @Inject constructor(
    private val api: WeatherAPI
) : IRealtimeWeatherRepository {
    override suspend fun getRealtimeWeather(
        latitude: Float,
        longitude: Float
    ): Resource<RealtimeWeatherDomain> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = api.getRealtimeData(q = "$latitude,$longitude")
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