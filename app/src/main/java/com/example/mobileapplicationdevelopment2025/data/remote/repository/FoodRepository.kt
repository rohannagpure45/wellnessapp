package com.example.mobileapplicationdevelopment2025.data.remote.repository

import com.example.mobileapplicationdevelopment2025.data.remote.FoodApiService
import com.example.mobileapplicationdevelopment2025.data.remote.model.FoodDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepository @Inject constructor(
    private val api: FoodApiService
) {
    suspend fun search(query: String): List<FoodDto> =
        api.searchFood(query).items
}
