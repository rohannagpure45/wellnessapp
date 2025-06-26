package com.example.mobileapplicationdevelopment2025.data.remote

object ApiConstants {
    const val BASE_URL = "https://api.calorieninjas.com/v1/"
    const val API_KEY = "ggC/uB7RrxJHXRPAY5Flpg==Mhj07mvygaRs8pRe"

    init {
        require(API_KEY.isNotBlank() && API_KEY != "ggC/uB7RrxJHXRPAY5Flpg==Mhj07mvygaRs8pRe") {
        }
    }
}
