package com.example.mobileapplicationdevelopment2025.di

import android.content.Context
import androidx.room.Room
import com.example.mobileapplicationdevelopment2025.data.local.AppDatabase
import com.example.mobileapplicationdevelopment2025.data.local.EquipmentDao
import com.example.mobileapplicationdevelopment2025.data.local.FoodDao
import com.example.mobileapplicationdevelopment2025.data.remote.EquipmentApi
import com.example.mobileapplicationdevelopment2025.data.remote.FoodApiService
import com.example.mobileapplicationdevelopment2025.data.remote.repository.EquipmentRepository
import com.example.mobileapplicationdevelopment2025.data.remote.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "wellness-db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideFoodDao(db: AppDatabase): FoodDao = db.foodDao()

    @Provides
    fun provideEquipmentDao(db: AppDatabase): EquipmentDao = db.equipmentDao()


    @Provides
    @Singleton
    fun provideFoodRepository(
        api: FoodApiService,
        dao: FoodDao
    ): FoodRepository = FoodRepository(api, dao)

    @Provides
    @Singleton
    fun provideEquipmentRepository(
        api: EquipmentApi,
        dao: EquipmentDao
    ): EquipmentRepository = EquipmentRepository(api, dao)
}
