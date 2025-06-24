package com.example.mobileapplicationdevelopment2025.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WellnessItem::class], version = 1, exportSchema = false)
abstract class WellnessDatabase : RoomDatabase() {
    abstract fun wellnessDao(): WellnessDao

    companion object {
        @Volatile private var INSTANCE: WellnessDatabase? = null

        fun getInstance(context: Context): WellnessDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    WellnessDatabase::class.java,
                    "wellness.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
