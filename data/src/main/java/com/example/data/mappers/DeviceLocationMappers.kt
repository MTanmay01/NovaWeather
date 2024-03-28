package com.example.data.mappers

import android.location.Location
import com.example.domain.models.DeviceLocation

fun Location.toDeviceLocation() = DeviceLocation(
    latitude = this.latitude.toFloat(),
    longitude = this.longitude.toFloat()
)