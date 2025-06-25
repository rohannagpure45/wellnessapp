package com.example.mobileapplicationdevelopment2025.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val calories: Int,
    val image: String
)
