package com.example.mobileapplicationdevelopment2025.data

data class Equipment(
    val id: Int,
    val name: String,
    val category: String,
    val description: String,
    val imageRes: Int,
    val addedCount: Int = 0
)