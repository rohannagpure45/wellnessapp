package com.example.mobileapplicationdevelopment2025.data.remote

data class FoodResponse(
    val items: List<FoodDto>
)

data class FoodDto(
    val id: String,
    val name: String,
    val calories: Double
)
