package com.example.mobileapplicationdevelopment2025.data.remote.model

import com.example.mobileapplicationdevelopment2025.data.local.FoodEntity

data class FoodDto(
    val id: String,
    val name: String,
    val imageUrl: String,
    val calories: Double,
    val serving_size_g: Double,
    val protein_g: Double,
    val fat_total_g: Double,
    val carbohydrates_total_g: Double
) {
    fun toEntity(): FoodEntity {
        return FoodEntity(id, name, imageUrl, calories, serving_size_g, protein_g, fat_total_g, carbohydrates_total_g)
    }
}
