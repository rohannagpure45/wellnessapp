package com.example.mobileapplicationdevelopment2025.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EquipmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: EquipmentEntity)

    @Query("SELECT * FROM equipment")
    fun getAll(): Flow<List<EquipmentEntity>>
}