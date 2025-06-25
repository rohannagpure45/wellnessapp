package com.example.mobileapplicationdevelopment2025.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.example.mobileapplicationdevelopment2025.data.WellnessItem

interface WellnessApi {
    @GET("wellness-items")
    suspend fun getWellnessItems(): List<WellnessItem>

    companion object {
        private const val BASE_URL = "https://example.com/api/"  // Placeholder base URL

        fun create(): WellnessApi {
            val client = OkHttpClient.Builder().build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WellnessApi::class.java)
        }
    }
}
