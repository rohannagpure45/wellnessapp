package com.example.mobileapplicationdevelopment2025.data.remote

import com.example.mobileapplicationdevelopment2025.data.remote.model.EquipmentDto
import retrofit2.http.GET
import retrofit2.http.Path

interface EquipmentApi {
    @GET("equipment")
    suspend fun fetchEquipment(): List<EquipmentDto>

    @GET("equipment/{id}")

    suspend fun fetchEquipmentDetail(@retrofit2.http.Path("id") id: Int): EquipmentDto

}
