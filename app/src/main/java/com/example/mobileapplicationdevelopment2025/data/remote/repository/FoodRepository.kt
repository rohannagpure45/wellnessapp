package com.example.mobileapplicationdevelopment2025.data.remote.repository

import com.example.mobileapplicationdevelopment2025.data.local.FoodDao
import com.example.mobileapplicationdevelopment2025.data.local.FoodEntity
import com.example.mobileapplicationdevelopment2025.data.remote.FoodApiService
import com.example.mobileapplicationdevelopment2025.data.remote.FoodDto as ApiFoodDto
import com.example.mobileapplicationdevelopment2025.data.remote.model.FoodDto
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException

@Singleton
class FoodRepository @Inject constructor(
    private val api: FoodApiService,
    private val dao: FoodDao
) {

    val foods: Flow<List<FoodDto>> =
        dao.getAll().map { it.map(FoodEntity::toModel) }

    suspend fun search(query: String): List<FoodDto> {
        val response = api.searchFood(query)
        if (!response.isSuccessful) throw HttpException(response)

        val apiItems  = response.body().orEmpty()
        val modelList = apiItems.map(ApiFoodDto::toModel)

        dao.replaceAll(modelList.map(FoodDto::toEntity))
        return modelList
    }

    suspend fun refresh() {
        val response = api.getAllFood()
        if (response.isSuccessful) {
            val apiItems  = response.body().orEmpty()
            val modelList = apiItems.map(ApiFoodDto::toModel)
            dao.replaceAll(modelList.map(FoodDto::toEntity))
        }
    }
}


private fun ApiFoodDto.toModel() = FoodDto(
    id                     = id,
    name                   = name,
    imageUrl               = "",
    calories               = calories,
    serving_size_g         = 0.0,
    protein_g              = 0.0,
    fat_total_g            = 0.0,
    carbohydrates_total_g  = 0.0
)

private fun FoodEntity.toModel() = FoodDto(
    id,
    name,
    imageUrl,
    calories,
    serving_size_g,
    protein_g,
    fat_total_g,
    carbohydrates_total_g
)

private fun FoodDto.toEntity() = FoodEntity(
    id,
    name,
    imageUrl,
    calories,
    serving_size_g,
    protein_g,
    fat_total_g,
    carbohydrates_total_g
)
