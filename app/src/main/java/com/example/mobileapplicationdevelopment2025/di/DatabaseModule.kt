package com.example.mobileapplicationdevelopment2025.di

import android.content.Context
import androidx.room.Room
import com.example.mobileapplicationdevelopment2025.data.local.AppDatabase
import com.example.mobileapplicationdevelopment2025.data.local.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "wellness.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideFoodDao(db: AppDatabase): FoodDao = db.foodDao()
}
