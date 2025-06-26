package com.example.mobileapplicationdevelopment2025.data.remote.repository

import com.example.mobileapplicationdevelopment2025.data.remote.EquipmentApi
import com.example.mobileapplicationdevelopment2025.data.remote.ImageApi
import com.example.mobileapplicationdevelopment2025.data.remote.model.EquipmentDto
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class EquipmentRepository @Inject constructor(
    private val api: EquipmentApi,
    private val imageApi: ImageApi
) {
    suspend fun refresh(): List<EquipmentDto> = withContext(Dispatchers.IO) {
        try {
            api.fetchEquipment().results
        } catch (_: Exception) {
            // Fallback to sample equipment data
            getSampleEquipment()
        }
    }
    
    private suspend fun getSampleEquipment(): List<EquipmentDto> {
        val equipmentNames = listOf(
            "Treadmill" to 600.0,
            "Stationary Bike" to 400.0,
            "Elliptical Machine" to 500.0,
            "Rowing Machine" to 550.0,
            "Weight Bench" to 300.0,
            "Dumbbells" to 250.0
        )
        
        return equipmentNames.map { (name, calories) ->
            val imageUrl = try {
                val resp = imageApi.find("$name gym equipment", 1, "doQk3_vPvpT9-AyGcCUXUCkSV1Vl6GS90BHIdKx8HCk")
                resp.body()?.results?.firstOrNull()?.urls?.small ?: "https://via.placeholder.com/150"
            } catch (e: Exception) {
                "https://via.placeholder.com/150"
            }
            
            EquipmentDto(
                name = name,
                caloriesBurnedPerHour = calories,
                imageUrl = imageUrl
            )
        }
    }
}
