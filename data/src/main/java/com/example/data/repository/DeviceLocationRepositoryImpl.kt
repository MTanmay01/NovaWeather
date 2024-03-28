package com.example.data.repository

import android.os.Looper
import com.example.data.mappers.toDeviceLocation
import com.example.domain.models.DeviceLocation
import com.example.domain.repository.IDeviceLocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeviceLocationRepositoryImpl @Inject constructor(
    private val fusedClient: FusedLocationProviderClient
) : IDeviceLocationRepository {

    @Suppress("MissingPermission")
    override fun fetchLocation(onLocationResult: (DeviceLocation) -> Unit) {
        val request = LocationRequest.Builder(1000)
            .setPriority(Priority.PRIORITY_LOW_POWER)
            .setMaxUpdates(1)
            .build()
        fusedClient.requestLocationUpdates(
            request,
            object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    fusedClient.removeLocationUpdates(this)
                    result.lastLocation?.toDeviceLocation()?.let(onLocationResult)
                }
            },
            Looper.myLooper()
        )
    }
}