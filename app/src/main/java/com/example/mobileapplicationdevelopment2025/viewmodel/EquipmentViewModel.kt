package com.example.mobileapplicationdevelopment2025.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import com.example.mobileapplicationdevelopment2025.data.remote.model.EquipmentDto
import com.example.mobileapplicationdevelopment2025.data.remote.repository.EquipmentRepository
import com.example.mobileapplicationdevelopment2025.session.SessionManager
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class EquipmentViewModel @Inject constructor(
    private val repository: EquipmentRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _equipment = MutableStateFlow<List<EquipmentDto>>(emptyList())
    val equipment: StateFlow<List<EquipmentDto>> get() = _equipment

    private val _burned = MutableStateFlow(0)
    val burned: StateFlow<Int> get() = _burned
    
    // Current exercise time settings
    val exerciseTimeMinutes: StateFlow<Int> = sessionManager.exerciseTimeMinutes
    val exerciseTimeMultiplier: StateFlow<Float> = sessionManager.exerciseTimeMultiplier

    init {
        viewModelScope.launch {
            _equipment.value = repository.refresh()
        }
    }

    fun addEquipment(item: EquipmentDto) {
        viewModelScope.launch {
            // Calculate actual calories burned based on exercise time
            val multiplier = sessionManager.exerciseTimeMultiplier.value
            val actualCaloriesBurned = (item.caloriesBurnedPerHour * multiplier).roundToInt()
            
            sessionManager.addBurned(actualCaloriesBurned)
            _burned.value += actualCaloriesBurned
        }
    }
}
