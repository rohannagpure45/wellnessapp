package com.example.mobileapplicationdevelopment2025.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {
    @GET("search/photos")
    suspend fun find(
        @Query("query") q: String,
        @Query("per_page") per: Int = 1,
        @Query("client_id") key: String = "<UNSPLASH_ACCESS_KEY>"
    ): Response<UnsplashResp>
}

data class UnsplashResp(val results: List<UnsplashPhoto>)
data class UnsplashPhoto(val urls: Urls)
data class Urls(val small: String)
