package com.example.data.api

import com.example.data.models.ForecastWeatherData
import com.example.data.models.RealtimeWeatherData
import com.example.presentation.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("current.json")
    suspend fun getRealtimeData(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("aqi") aqi: String = "yes",
        @Query("q") q: String
    ): Response<RealtimeWeatherData>

    @GET("forecast.json")
    suspend fun getForecastData(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("aqi") aqi: String = "yes",
        @Query("days") days: String = "5",
        @Query("q") q: String
    ): Response<ForecastWeatherData>

}