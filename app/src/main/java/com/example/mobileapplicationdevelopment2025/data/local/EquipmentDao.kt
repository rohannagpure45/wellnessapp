package com.example.mobileapplicationdevelopment2025.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface EquipmentDao {
    @Query("SELECT * FROM equipment")
    fun getAll(): Flow<List<EquipmentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: EquipmentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(list: List<EquipmentEntity>)

    @Query("DELETE FROM equipment")
    suspend fun clearTable()

    @Transaction
    suspend fun replaceAll(list: List<EquipmentEntity>) {
        clearTable()
        upsertAll(list)
    }
}
