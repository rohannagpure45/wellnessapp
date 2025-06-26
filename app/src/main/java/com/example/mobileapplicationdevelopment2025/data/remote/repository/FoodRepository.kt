package com.example.mobileapplicationdevelopment2025.data.remote.repository

import com.example.mobileapplicationdevelopment2025.data.local.FoodDao
import com.example.mobileapplicationdevelopment2025.data.local.FoodEntity
import com.example.mobileapplicationdevelopment2025.data.remote.FoodApiService
import com.example.mobileapplicationdevelopment2025.data.remote.model.FoodDto    // ← use the existing DTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepository @Inject constructor(
    private val api: FoodApiService,
    private val dao: FoodDao
) {
    suspend fun search(query: String): List<FoodDto> = try {
        val res = api.searchFood(query)
        val list = res.body()?.items.orEmpty()
        dao.replaceAll(list.map { it.toEntity() })
        list
    } catch (_: Exception) {
        emptyList()
    }
}

private fun FoodDto.toEntity() = FoodEntity(
    name     = name,
    calories = calories,
    img      = "https://source.unsplash.com/100x100/?$name"
)
