package com.example.mobileapplicationdevelopment2025.repository

import com.example.mobileapplicationdevelopment2025.data.WellnessDao
import com.example.mobileapplicationdevelopment2025.data.WellnessItem
import com.example.mobileapplicationdevelopment2025.network.WellnessApi
import kotlinx.coroutines.flow.Flow

class WellnessRepository(private val api: WellnessApi, private val dao: WellnessDao) {

    fun getItemsByCategory(category: String): Flow<List<WellnessItem>> {
        return if (category.isBlank()) dao.getAllItems()
        else dao.getItemsByCategory(category)
    }

    suspend fun refreshItems() {
        try {
            val items = api.getWellnessItems()
            dao.deleteAllItems()
            dao.insertItems(items)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
