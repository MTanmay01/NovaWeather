package com.example.domain.repository

import com.example.domain.models.DeviceLocation

interface IDeviceLocationRepository {
    fun fetchLocation(onLocationResult: (DeviceLocation) -> Unit)
}