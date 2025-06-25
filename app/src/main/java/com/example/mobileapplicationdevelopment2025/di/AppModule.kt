package com.example.mobileapplicationdevelopment2025.di

import android.content.Context
import androidx.room.Room
import com.example.mobileapplicationdevelopment2025.data.local.AppDatabase
import com.example.mobileapplicationdevelopment2025.data.local.EquipmentDao
import com.example.mobileapplicationdevelopment2025.data.remote.EquipmentApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.mobileapplicationdevelopment2025.data.local.FoodDao
private const val BASE_URL = "https://api.example.com/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides @Singleton
    fun provideEquipmentApi(retrofit: Retrofit): EquipmentApi =
        retrofit.create(EquipmentApi::class.java)




    @Provides @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "wellness.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideEquipmentDao(db: AppDatabase): EquipmentDao = db.equipmentDao()

    @Provides
    fun provideFoodDao(db: AppDatabase): FoodDao = db.foodDao()
}
