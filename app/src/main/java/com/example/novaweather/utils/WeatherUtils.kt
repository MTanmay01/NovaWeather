package com.example.novaweather.utils

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.util.Log

object WeatherUtils {

    fun fetchWeatherData(
        locationManager: LocationManager,
        fetchRealtimeWeather: (Float, Float) -> Unit,
        fetchForecastWeather: (Float, Float) -> Unit
    ) {
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                Log.d("HomeViewModel", "LOCATION FETCHED: ${location.latitude}, ${location.longitude}")
                locationManager.removeUpdates(this)
                fetchRealtimeWeather(
                    location.latitude.toFloat(),
                    location.longitude.toFloat()
                )
                fetchForecastWeather(
                    location.latitude.toFloat(),
                    location.longitude.toFloat()
                )
            }

            override fun onProviderDisabled(provider: String) {}
        }

        val provider =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) LocationManager.FUSED_PROVIDER
            else LocationManager.NETWORK_PROVIDER

        try {
            Log.d("HomeViewModel", "FETCHING LOCATION..")
            locationManager.requestLocationUpdates(
                provider, 0, 0f, locationListener
            )
        } catch (e: SecurityException) {
            Log.e(e.javaClass.simpleName, "Permission not granted", )
            e.printStackTrace()
        } catch (e: RuntimeException) {
            Log.e(e.javaClass.simpleName, "fetchCurrentLocation: Caller thread has no Lopper")
            e.printStackTrace()
        }
    }
}