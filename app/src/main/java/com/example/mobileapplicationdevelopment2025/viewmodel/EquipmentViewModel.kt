package com.example.mobileapplicationdevelopment2025.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.mobileapplicationdevelopment2025.R
import com.example.mobileapplicationdevelopment2025.data.Equipment

class EquipmentViewModel : ViewModel() {
    private val _equipment = MutableStateFlow(getInitialEquipment())
    val equipment: StateFlow<List<Equipment>> = _equipment.asStateFlow()

    private fun getInitialEquipment(): List<Equipment> {
        return listOf(
            Equipment(
                id = 1,
                name = "Treadmill",
                category = "Cardio",
                description = "Electric treadmill for running and walking",
                imageRes = R.drawable.ic_treadmill
            ),
            Equipment(
                id = 2,
                name = "Dumbbells",
                category = "Strength",
                description = "Set of adjustable dumbbells",
                imageRes = R.drawable.ic_dumbbells
            ),
            Equipment(
                id = 3,
                name = "Exercise Bike",
                category = "Cardio",
                description = "Stationary exercise bike",
                imageRes = R.drawable.ic_bike
            ),
            Equipment(
                id = 4,
                name = "Barbell",
                category = "Strength",
                description = "Olympic barbell with weight plates",
                imageRes = R.drawable.ic_barbell
            ),
            Equipment(
                id = 5,
                name = "Yoga Mat",
                category = "Flexibility",
                description = "Non-slip yoga and exercise mat",
                imageRes = R.drawable.ic_yoga_mat
            ),
            Equipment(
                id = 6,
                name = "Bench Press",
                category = "Strength",
                description = "Adjustable weight bench",
                imageRes = R.drawable.ic_bench
            )
        )
    }

    fun addEquipment(equipment: Equipment) {
        val currentList = _equipment.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == equipment.id }
        if (index != -1) {
            currentList[index] = equipment.copy(addedCount = equipment.addedCount + 1)
            _equipment.value = currentList
        }
    }

    fun removeEquipment(equipment: Equipment) {
        val currentList = _equipment.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == equipment.id }
        if (index != -1 && equipment.addedCount > 0) {
            currentList[index] = equipment.copy(addedCount = equipment.addedCount - 1)
            _equipment.value = currentList
        }
    }
}