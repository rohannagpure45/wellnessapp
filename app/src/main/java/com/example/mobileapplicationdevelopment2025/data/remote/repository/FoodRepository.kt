package com.example.mobileapplicationdevelopment2025.data.remote.repository


import com.example.mobileapplicationdevelopment2025.data.remote.FoodApiService
import com.example.mobileapplicationdevelopment2025.data.remote.model.FoodDto
import com.example.mobileapplicationdevelopment2025.data.local.FoodDao
import com.example.mobileapplicationdevelopment2025.data.local.FoodEntity
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

    suspend fun search(query: String) {
        val result = api.searchFood(query)
        if (!result.isSuccessful) {
            throw RuntimeException("HTTP ${result.code()} ${result.message()}")
        }

        val body = result.body().orEmpty()
        dao.replaceAll(body.map(FoodDto::toEntity))
    }

    suspend fun refresh() {
        val result = api.getAllFood()
        if (result.isSuccessful) {
            dao.replaceAll(result.body().orEmpty().map(FoodDto::toEntity))
        }
    }
}



private fun FoodEntity.toDto() = FoodDto(id, name, imageUrl, calories)
private fun FoodDto.toEntity() = FoodEntity(id, name, imageUrl, calories)
