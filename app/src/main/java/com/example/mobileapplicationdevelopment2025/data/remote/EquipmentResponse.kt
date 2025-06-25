package com.example.mobileapplicationdevelopment2025.data.remote

data class EquipmentResponse(
    val results: List<EquipmentDto>
)

data class EquipmentDto(
    val id: Int,
    val name: String,
    val category: String,
    val imageUrl: String,
    val description: String
) {
    fun toEntity() = com.example.mobileapplicationdevelopment2025.data.local.EquipmentEntity(
        equipmentId = id,
        name = name,
        category = category,
        imageUrl = imageUrl,
        description = description
    )
}
