package com.example.mobileapplicationdevelopment2025.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipment")
data class EquipmentEntity(
    @PrimaryKey val id: String,
    val name: String,
    val imageUrl: String,
    val description: String
)
