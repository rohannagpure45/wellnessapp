package com.example.mobileapplicationdevelopment2025.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import androidx.room.Transaction

@Dao
interface FoodDao {
    @Query("SELECT * FROM food")
    fun getAll(): Flow<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(list: List<FoodEntity>)

    @Transaction
    suspend fun replaceAll(list: List<FoodEntity>) {
        clearTable()
        upsertAll(list)
    }

    @Query("DELETE FROM food")
    suspend fun clearTable()
}
