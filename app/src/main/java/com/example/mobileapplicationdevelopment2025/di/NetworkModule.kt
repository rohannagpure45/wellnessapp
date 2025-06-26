package com.example.mobileapplicationdevelopment2025.di

import com.example.mobileapplicationdevelopment2025.data.remote.FoodApiService
import com.example.mobileapplicationdevelopment2025.data.remote.EquipmentApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.calorieninjas.com/v1/"
    private const val API_KEY  = "ggC/uB7RrxJHXRPAY5Flpg==Mhj07mvygaRs8pRe"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val req = chain.request().newBuilder()
                    .addHeader("X-Api-Key", API_KEY)
                    .build()
                chain.proceed(req)
            })
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
    fun provideFoodApi(retrofit: Retrofit): FoodApiService =
        retrofit.create(FoodApiService::class.java)

    @Provides
    @Singleton
    fun provideEquipmentApi(retrofit: Retrofit): EquipmentApi =
        retrofit.create(EquipmentApi::class.java)
}
