package com.example.mobileapplicationdevelopment2025.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodEntity(
     @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val calories: Int,
    val image: String
)
