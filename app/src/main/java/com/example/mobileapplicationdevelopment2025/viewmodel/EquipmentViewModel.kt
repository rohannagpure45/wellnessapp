package com.example.mobileapplicationdevelopment2025.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapplicationdevelopment2025.data.CalorieTracker
import com.example.mobileapplicationdevelopment2025.data.remote.model.EquipmentDto
import com.example.mobileapplicationdevelopment2025.data.remote.repository.EquipmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class EquipmentViewModel @Inject constructor(
    private val repo: EquipmentRepository
) : ViewModel() {

    private val burnRate = mapOf(
        "Barbell" to 126.0, "Bench" to 80.0, "Dumbbell" to 110.0,
        "Gym mat" to 60.0, "Incline bench" to 90.0, "Kettlebell" to 135.0,
        "Pull-up bar" to 100.0, "Resistance band" to 70.0,
        "SZ-Bar" to 120.0, "Swiss Ball" to 50.0
    )

    private val _items = MutableStateFlow<List<EquipmentDto>>(emptyList())
    val items: StateFlow<List<EquipmentDto>> = _items

    private val _added = MutableStateFlow<Map<String, Int>>(emptyMap())
    val added: StateFlow<Map<String, Int>> = _added

    val total: StateFlow<Double> = _added.map { cart ->
        cart.entries.sumOf { (name, qty) -> (burnRate[name] ?: 80.0) * qty }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)

    init {
        viewModelScope.launch {
            _items.value = repo.refresh()
        }
    }

    fun add(name: String) {
        _added.value = _added.value.toMutableMap().apply {
            put(name, (get(name) ?: 0) + 1)
        }
        CalorieTracker.burned.value += burnRate[name] ?: 80.0
    }

    fun img(name: String): String =
        "https://picsum.photos/seed/${kotlin.math.abs(name.hashCode() * 37)}/100/100"
}
