package com.example.mobileapplicationdevelopment2025.repository

import com.example.mobileapplicationdevelopment2025.data.local.FoodDao
import com.example.mobileapplicationdevelopment2025.data.local.FoodEntity
import com.example.mobileapplicationdevelopment2025.data.remote.FoodApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class FoodRepository(
    private val api: FoodApiService,
    private val dao: FoodDao
) {
    fun observeFood(): Flow<List<FoodEntity>> = dao.observeAll()

    suspend fun refresh() {
        val remote = api.getFood()
        val local = remote.map { FoodEntity(it.id, it.name, it.calories, it.image) }
        dao.replaceAll(local)
    }
    suspend fun ensureSeeded() {
        if (dao.observeAll().first().isEmpty()) refresh()
    }
}
