package com.example.mobileapplicationdevelopment2025.data.remote

import com.example.mobileapplicationdevelopment2025.data.remote.model.EquipmentDto
import retrofit2.http.GET

interface EquipmentApi {
    @GET("equipment")
    suspend fun fetchEquipment(): List<EquipmentDto>
}
