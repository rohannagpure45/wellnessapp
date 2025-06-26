package com.example.mobileapplicationdevelopment2025.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class FoodEntity(
    @PrimaryKey val name: String,
    val calories: Double,
    val img: String
)

