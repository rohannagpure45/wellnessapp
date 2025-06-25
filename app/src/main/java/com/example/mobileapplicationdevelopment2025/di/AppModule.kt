package com.example.mobileapplicationdevelopment2025.di

import android.content.Context
import androidx.room.Room
import com.example.mobileapplicationdevelopment2025.BuildConfig
import com.example.mobileapplicationdevelopment2025.data.local.AppDatabase
import com.example.mobileapplicationdevelopment2025.data.local.FoodDao
import com.example.mobileapplicationdevelopment2025.data.remote.BASE_URL
import com.example.mobileapplicationdevelopment2025.data.remote.CalorieApiService
import com.example.mobileapplicationdevelopment2025.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("X-Api-Key", BuildConfig.CALORIE_API_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCalorieApi(retrofit: Retrofit): CalorieApiService =
        retrofit.create(CalorieApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "food.db").build()

    @Provides
    fun provideFoodDao(db: AppDatabase): FoodDao = db.foodDao()

    @Provides
    @Singleton
    fun provideFoodRepository(
        api: CalorieApiService,
        dao: FoodDao
    ): FoodRepository = FoodRepository(api, dao)
}
