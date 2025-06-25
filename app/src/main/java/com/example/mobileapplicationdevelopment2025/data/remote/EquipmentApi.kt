package com.example.mobileapplicationdevelopment2025.data.remote

import retrofit2.http.GET

interface EquipmentApi {
    @GET("equipment")
    suspend fun fetchEquipment(): List<EquipmentDto>
}
