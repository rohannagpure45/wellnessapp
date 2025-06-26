package com.example.mobileapplicationdevelopment2025.data.remote

import com.example.mobileapplicationdevelopment2025.data.remote.model.CalorieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApiService {
    @GET("nutrition")
    suspend fun searchFood(@Query("query") query: String): CalorieResponse
}
