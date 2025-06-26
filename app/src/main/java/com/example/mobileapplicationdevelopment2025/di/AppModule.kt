package com.example.mobileapplicationdevelopment2025.di

import com.example.mobileapplicationdevelopment2025.data.local.FoodDao
import com.example.mobileapplicationdevelopment2025.data.remote.ImageApi
import com.example.mobileapplicationdevelopment2025.data.remote.FoodApiService
import com.example.mobileapplicationdevelopment2025.data.remote.repository.FoodRepository
import com.example.mobileapplicationdevelopment2025.data.remote.EquipmentApi
import com.example.mobileapplicationdevelopment2025.data.remote.repository.EquipmentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFoodRepository(
        api: FoodApiService,
        dao: FoodDao
    ): FoodRepository = FoodRepository(api, dao)

    @Singleton
    @Provides
    fun provideEquipmentRepository(
        api: EquipmentApi
    ): EquipmentRepository = EquipmentRepository(api)
}
