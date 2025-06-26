package com.example.mobileapplicationdevelopment2025.di

import com.example.mobileapplicationdevelopment2025.data.remote.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

private const val CALORIES = "calories"
private const val UNSPLASH = "unsplash"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton @Provides
    fun ok() = OkHttpClient.Builder().build()

    @Singleton @Provides @Named(CALORIES)
    fun calorieRetrofit(ok: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.calorieninjas.com/v1/")
            .client(ok)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton @Provides @Named(UNSPLASH)
    fun unsplashRetrofit(ok: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .client(ok)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton @Provides
    fun provideFoodApi(@Named(CALORIES) r: Retrofit): FoodApiService =
        r.create(FoodApiService::class.java)

    @Singleton @Provides
    fun provideEquipmentApi(@Named(CALORIES) r: Retrofit): EquipmentApi =
        r.create(EquipmentApi::class.java)

    @Singleton @Provides
    fun provideImageApi(@Named(UNSPLASH) r: Retrofit): ImageApi =
        r.create(ImageApi::class.java)
}
