package com.example.mobileapplicationdevelopment2025.data.remote.repository

import com.example.mobileapplicationdevelopment2025.data.local.FoodDao
import com.example.mobileapplicationdevelopment2025.data.local.FoodEntity
import com.example.mobileapplicationdevelopment2025.data.remote.FoodApiService
import com.example.mobileapplicationdevelopment2025.data.remote.model.FoodDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepository @Inject constructor(
    private val api: FoodApiService,
    private val dao: FoodDao
) {


    val foods: Flow<List<FoodDto>> =
        dao.getAll()
            .map { list -> list.map(FoodEntity::toDto) }


    suspend fun search(query: String): List<FoodDto> {
        val response = api.searchFood(query)
        if (!response.isSuccessful) {
            throw RuntimeException("HTTP ${response.code()} ${response.message()}")
        }

        val items = response.body().orEmpty()
        dao.replaceAll(items.map(FoodDto::toEntity))
        return items
    }

    suspend fun refresh() {
        val response = api.getAllFood()
        if (response.isSuccessful) {
            dao.replaceAll(response.body().orEmpty().map(FoodDto::toEntity))
        }
    }
}



private fun FoodEntity.toDto() = FoodDto(
    name     = name,
    calories = calories
)


private fun FoodDto.toEntity() = FoodEntity(
    name     = name,
    calories = calories,
    image    = ""
)
