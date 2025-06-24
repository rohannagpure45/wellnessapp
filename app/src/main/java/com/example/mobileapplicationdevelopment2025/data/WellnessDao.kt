package com.example.mobileapplicationdevelopment2025.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WellnessDao {
    @Query("SELECT * FROM wellness_items")
    fun getAllItems(): Flow<List<WellnessItem>>

    @Query("SELECT * FROM wellness_items WHERE category = :category")
    fun getItemsByCategory(category: String): Flow<List<WellnessItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<WellnessItem>)

    @Query("DELETE FROM wellness_items")
    suspend fun deleteAllItems()
}
