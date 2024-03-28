package com.example.data.di

import android.content.Context
import android.location.LocationManager
import com.example.data.api.WeatherAPI
import com.example.data.repository.DeviceLocationRepositoryImpl
import com.example.data.repository.ForecastWeatherRepositoryImpl
import com.example.data.repository.RealtimeWeatherRepositoryImpl
import com.example.domain.repository.IDeviceLocationRepository
import com.example.domain.repository.IForecastWeatherRepository
import com.example.domain.repository.IRealtimeWeatherRepository
import com.example.domain.usecase.GetDeviceLocationUseCase
import com.example.presentation.BuildConfig
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DependencyProvider {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesWeatherApi(retrofit: Retrofit): WeatherAPI =
        retrofit.create(WeatherAPI::class.java)

    @Provides
    @Singleton
    fun providesRealtimeWeatherRepository(api: WeatherAPI): IRealtimeWeatherRepository =
        RealtimeWeatherRepositoryImpl(api)

    @Provides
    @Singleton
    fun providesForecastWeatherRepository(api: WeatherAPI): IForecastWeatherRepository =
        ForecastWeatherRepositoryImpl(api)

    @Provides
    @Singleton
    fun providesDeviceLocationRepository(client: FusedLocationProviderClient): IDeviceLocationRepository =
        DeviceLocationRepositoryImpl(client)

    @Provides
    @Singleton
    fun providesLocationManager(@ApplicationContext context: Context): LocationManager {
        return context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @Provides
    @Singleton
    fun providesFusedLocationClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    @Singleton
    fun providesLocationUseCase(repository: IDeviceLocationRepository): GetDeviceLocationUseCase =
        GetDeviceLocationUseCase(repository)

}