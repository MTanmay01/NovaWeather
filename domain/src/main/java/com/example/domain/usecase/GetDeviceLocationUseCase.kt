package com.example.domain.usecase

import com.example.domain.models.DeviceLocation
import com.example.domain.repository.IDeviceLocationRepository

class GetDeviceLocationUseCase (
    private val repo: IDeviceLocationRepository
) {
    operator fun invoke(onLocationResult: (DeviceLocation) -> Unit) {
        repo.fetchLocation { onLocationResult(it) }
    }
}