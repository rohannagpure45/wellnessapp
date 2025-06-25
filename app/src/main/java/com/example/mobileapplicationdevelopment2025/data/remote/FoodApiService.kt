package com.example.mobileapplicationdevelopment2025.data.remote

import com.example.mobileapplicationdevelopment2025.data.remote.dto.FoodDto
import retrofit2.http.GET
import retrofit2.http.Header

interface FoodApiService {
    @GET("v1/food")
    suspend fun getFood(
        @Header("X-Api-Key") apiKey: String = BuildConfig.CALORIE_API_KEY
    ): List<FoodDto>
}
