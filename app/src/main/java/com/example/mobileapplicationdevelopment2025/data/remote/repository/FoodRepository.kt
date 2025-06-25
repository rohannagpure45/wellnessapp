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
    val foods: Flow<List<FoodDto>> = dao.getAll().map { entities ->
        entities.map { it.toDto() }
    }

    suspend fun search(query: String): List<FoodDto> {
        val result = api.searchFood(query)
        if (!result.isSuccessful) {
            throw RuntimeException("HTTP ${result.code()} ${result.message()}")
        }
        val body: List<FoodDto> = result.body().orEmpty()
        dao.replaceAll(body.map { it.toEntity() })
        return body
    }

    suspend fun refresh() {
        val result = api.getAllFood()
        if (result.isSuccessful) {
            val fullList: List<FoodDto> = result.body().orEmpty()
            dao.replaceAll(fullList.map { it.toEntity() })
        }
    }
}

private fun FoodEntity.toDto(): FoodDto {
    return FoodDto(id, name, imageUrl, calories, serving_size_g, protein_g, fat_total_g, carbohydrates_total_g)
}

private fun FoodDto.toEntity(): FoodEntity {
    return FoodEntity(id, name, imageUrl, calories, serving_size_g, protein_g, fat_total_g, carbohydrates_total_g)
}
