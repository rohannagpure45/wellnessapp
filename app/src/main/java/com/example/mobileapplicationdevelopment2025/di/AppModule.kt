package com.example.mobileapplicationdevelopment2025.di

import com.example.mobileapplicationdevelopment2025.data.remote.FoodApiService
import com.example.mobileapplicationdevelopment2025.data.remote.repository.FoodRepository
import com.example.mobileapplicationdevelopment2025.data.remote.EquipmentApi
import com.example.mobileapplicationdevelopment2025.data.remote.repository.EquipmentRepository
import com.example.mobileapplicationdevelopment2025.data.remote.ImageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFoodRepository(
        api: FoodApiService,
        imageApi: ImageApi
    ): FoodRepository = FoodRepository(api, imageApi)

    @Provides
    @Singleton
    fun provideEquipmentRepository(api: EquipmentApi): EquipmentRepository =
        EquipmentRepository(api)
}
