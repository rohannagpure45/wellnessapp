package com.example.mobileapplicationdevelopment2025.data.remote.repository

import com.example.mobileapplicationdevelopment2025.data.remote.FoodApiService
import com.example.mobileapplicationdevelopment2025.data.remote.ImageApi
import com.example.mobileapplicationdevelopment2025.data.remote.Urls
import com.example.mobileapplicationdevelopment2025.data.remote.model.FoodDto
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class FoodRepository @Inject constructor(
    private val api: FoodApiService,
    private val imageApi: ImageApi
) {
    suspend fun search(query: String): List<com.example.mobileapplicationdevelopment2025.data.remote.model.FoodDto> = withContext(Dispatchers.IO) {
        val foods = api.searchFood(query).items
        foods.map { food ->
            val imageUrl = try {
                val resp = imageApi.find(food.name, 1, "doQk3_vPvpT9-AyGcCUXUCkSV1Vl6GS90BHIdKx8HCk")
                resp.body()?.results?.firstOrNull()?.urls?.small ?: "https://via.placeholder.com/150"
            } catch (e: Exception) {
                "https://via.placeholder.com/150"
            }
            com.example.mobileapplicationdevelopment2025.data.remote.model.FoodDto(
                name = food.name,
                calories = food.calories,
                imageUrl = imageUrl
            )
        }
    }
}
