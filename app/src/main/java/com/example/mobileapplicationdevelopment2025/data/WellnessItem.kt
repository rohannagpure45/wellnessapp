package com.example.mobileapplicationdevelopment2025.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wellness_items")
data class WellnessItem(
    @PrimaryKey val id: String,
    val name: String,
    val category: String,
    val expiryDate: String?,
    val price: String,
    val warranty: String?,
    val imageUrl: String
)
