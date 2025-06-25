package com.example.mobileapplicationdevelopment2025.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodEntity(
    @PrimaryKey val id: String,
    val name: String,
    val imageUrl: String,
    val calories: Double,
    val serving_size_g: Double,
    val protein_g: Double,
    val fat_total_g: Double,
    val carbohydrates_total_g: Double
)
