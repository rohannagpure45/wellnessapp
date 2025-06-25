package com.example.mobileapplicationdevelopment2025.data.remote.model

import com.example.mobileapplicationdevelopment2025.data.local.EquipmentEntity

data class EquipmentDto(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    ) {
    fun toEntity() = EquipmentEntity(id, name, imageUrl, description)
}