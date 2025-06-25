package com.example.mobileapplicationdevelopment2025.di

import android.content.Context
import androidx.room.Room
import com.example.mobileapplicationdevelopment2025.data.local.AppDatabase
import com.example.mobileapplicationdevelopment2025.data.remote.EquipmentApi
import com.example.mobileapplicationdevelopment2025.data.remote.FoodApiService
import com.example.mobileapplicationdevelopment2025.data.remote.repository.EquipmentRepository
import com.example.mobileapplicationdevelopment2025.data.remote.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.calorieninjas.com/v1/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides @Singleton
    fun provideDatabase(appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides @Singleton
    fun provideFoodDao(db: AppDatabase) = db.foodDao()

    @Provides @Singleton
    fun provideEquipmentDao(db: AppDatabase) = db.equipmentDao()

    @Provides @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides @Singleton
    fun provideFoodApiService(retrofit: Retrofit): FoodApiService {
        return retrofit.create(FoodApiService::class.java)
    }

    @Provides @Singleton
    fun provideEquipmentApi(retrofit: Retrofit): EquipmentApi {
        return retrofit.create(EquipmentApi::class.java)
    }

    @Provides @Singleton
    fun provideFoodRepository(api: FoodApiService, dao: com.example.mobileapplicationdevelopment2025.data.local.FoodDao): FoodRepository {
        return FoodRepository(api, dao)
    }

    @Provides @Singleton
    fun provideEquipmentRepository(api: EquipmentApi, dao: com.example.mobileapplicationdevelopment2025.data.local.EquipmentDao): EquipmentRepository {
        return EquipmentRepository(api, dao)
    }
}
