package com.example.mobileapplicationdevelopment2025.data.remote

import com.example.mobileapplicationdevelopment2025.BuildConfig
import com.example.mobileapplicationdevelopment2025.data.remote.model.CalorieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FoodApiService {

    @GET("nutrition")
    suspend fun getFood(
        @Header("X-Api-Key") apiKey: String = BuildConfig.CALORIE_API_KEY,
        @Query("query") query: String
    ): CalorieResponse
}
