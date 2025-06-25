package com.example.mobileapplicationdevelopment2025.data.remote.repository

import com.example.mobileapplicationdevelopment2025.data.remote.EquipmentApi
import com.example.mobileapplicationdevelopment2025.data.remote.model.EquipmentDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EquipmentRepository @Inject constructor(
    private val api: EquipmentApi
) {

    suspend fun getEquipmentList(query: String? = null): List<EquipmentDto> =
        withContext(Dispatchers.IO) { api.fetchEquipment(query) }

    suspend fun getEquipmentById(id: String): EquipmentDto =
        withContext(Dispatchers.IO) { api.fetchEquipmentDetail(id) }
}
