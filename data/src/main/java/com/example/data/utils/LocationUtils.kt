package com.example.data.utils

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority

object LocationUtils {
    @SuppressLint("MissingPermission")
    fun fetchLocation(
        client: FusedLocationProviderClient,
        onLocationResult: (Location?) -> Unit
        ) {
        val request = LocationRequest.Builder(1000)
            .setPriority(Priority.PRIORITY_LOW_POWER)
            .setMaxUpdates(1)
            .build()
        client.requestLocationUpdates(
            request,
            object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    result.lastLocation.let(onLocationResult)
                }
            },
            Looper.getMainLooper()
        )
    }
}