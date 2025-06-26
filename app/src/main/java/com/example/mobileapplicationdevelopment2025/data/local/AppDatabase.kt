package com.example.mobileapplicationdevelopment2025.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FoodEntity::class, EquipmentEntity::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}
