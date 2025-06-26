package com.example.mobileapplicationdevelopment2025.data.remote.repository

import com.example.mobileapplicationdevelopment2025.data.remote.EquipmentApi
import com.example.mobileapplicationdevelopment2025.data.remote.model.EquipmentDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EquipmentRepository @Inject constructor(
    private val api: EquipmentApi
) {
    suspend fun refresh(): List<EquipmentDto> =
        try {
            api.fetchEquipment().results
        } catch (_: Exception) {
            emptyList()
        }
}
