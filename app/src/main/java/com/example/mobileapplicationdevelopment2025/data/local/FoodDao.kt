package com.example.mobileapplicationdevelopment2025.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * FROM foods")
    fun getAll(): Flow<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<FoodEntity>)

    @Query("DELETE FROM foods")
    suspend fun clearTable()

    @Transaction
    suspend fun replaceAll(list: List<FoodEntity>) {
        clearTable()
        insertAll(list)
    }
}
