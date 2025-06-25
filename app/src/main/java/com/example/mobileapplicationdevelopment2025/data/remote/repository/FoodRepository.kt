package com.example.mobileapplicationdevelopment2025.data.remote.repository


import com.example.mobileapplicationdevelopment2025.data.local.FoodDao
import com.example.mobileapplicationdevelopment2025.data.remote.CalorieApiService
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class FoodRepository @Inject constructor(
    private val api: CalorieApiService,
    private val dao: FoodDao
) {
    fun foods(): Flow<List<FoodDto>> = dao.observeAll().map { it.map(::toDto) }

    suspend fun refresh(query: String) {
        val remote = api.searchFood(query).items
        dao.upsertAll(remote.map(::toEntity))
    }
}
