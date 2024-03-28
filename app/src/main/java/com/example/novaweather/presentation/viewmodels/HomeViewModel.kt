package com.example.novaweather.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.ForecastWeatherDomain
import com.example.domain.models.RealtimeWeatherDomain
import com.example.domain.models.Resource
import com.example.domain.repository.IForecastWeatherRepository
import com.example.domain.repository.IRealtimeWeatherRepository
import com.example.domain.usecase.GetDeviceLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val realtimeRepository: IRealtimeWeatherRepository,
    private val forecastRepository: IForecastWeatherRepository,
    private val locationUseCase: GetDeviceLocationUseCase,
) : ViewModel() {

    init {
        fetchWeather()
    }

    private val _realtimeWeatherFlow =
        MutableStateFlow<Resource<RealtimeWeatherDomain>>(Resource.Loading)
    val realtimeWeatherFlow: StateFlow<Resource<RealtimeWeatherDomain>>
        get() = _realtimeWeatherFlow.asStateFlow()

    private val _forecastWeatherFlow =
        MutableStateFlow<Resource<ForecastWeatherDomain>>(Resource.Loading)
    val forecastWeatherFlow: StateFlow<Resource<ForecastWeatherDomain>>
        get() = _forecastWeatherFlow.asStateFlow()

    fun fetchWeather() {
        locationUseCase { location ->
            fetchRealtimeWeather(location.latitude, location.longitude)
            fetchForecastWeather(location.latitude, location.longitude)
        }
    }

    private fun fetchRealtimeWeather(
        latitude: Float,
        longitude: Float
    ) {
        viewModelScope.launch {
            delay(5000)
            when (val result = realtimeRepository.getRealtimeWeather(latitude, longitude)) {
                Resource.Loading -> {}
                is Resource.Success -> {
                    _realtimeWeatherFlow.update {
                        Resource.Success(result.data)
                    }
                }
                is Resource.Error -> {
                    _realtimeWeatherFlow.update {
                        Resource.Error(result.errorCode)
                    }
                }
            }
        }
    }

    private fun fetchForecastWeather(latitude: Float, longitude: Float) {
        viewModelScope.launch {
            when (val result = forecastRepository.getForecastData(latitude, longitude)) {
                Resource.Loading -> {}
                is Resource.Success -> {
                    _forecastWeatherFlow.update {
                        Resource.Success(result.data)
                    }
                }

                is Resource.Error -> {
                    _forecastWeatherFlow.update {
                        Resource.Error(result.errorCode)
                    }
                }
            }
        }
    }

}