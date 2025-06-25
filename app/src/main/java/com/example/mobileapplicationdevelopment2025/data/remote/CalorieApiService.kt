package com.example.mobileapplicationdevelopment2025.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface CalorieApiService {
    @GET("foods/search")
    suspend fun searchFood(
        @Query("query") query: String
    ): FoodResponse
}
