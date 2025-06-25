package com.example.mobileapplicationdevelopment2025.data.remote

import com.example.mobileapplicationdevelopment2025.BuildConfig
import com.example.mobileapplicationdevelopment2025.data.remote.model.CalorieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.Response

interface FoodApiService {
    @GET("food/search")
    suspend fun searchFood(
        @Query("q") query: String
    ): Response<List<FoodDto>>

    @GET("food/all")
    suspend fun getAllFood(): Response<List<FoodDto>>
}