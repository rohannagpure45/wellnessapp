package com.example.mobileapplicationdevelopment2025.session

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map

@Singleton
class SessionManager @Inject constructor() {
    private val _consumed = MutableStateFlow(0)
    val consumed: StateFlow<Int> get() = _consumed

    private val _burned = MutableStateFlow(0)
    val burned: StateFlow<Int> get() = _burned

    // Exercise time multiplier (minutes/60 to get fraction of an hour)
    private val _exerciseTimeMinutes = MutableStateFlow(60)
    val exerciseTimeMinutes: StateFlow<Int> get() = _exerciseTimeMinutes
    
    // Computed multiplier for calorie calculations
    val exerciseTimeMultiplier: StateFlow<Float> get() = _exerciseTimeMinutes
        .map { it / 60f }
        .stateIn(scope = GlobalScope, started = SharingStarted.Eagerly, initialValue = 1f)

    // Track added items for removal functionality
    private val _addedFoodItems = MutableStateFlow<List<AddedFoodItem>>(emptyList())
    val addedFoodItems: StateFlow<List<AddedFoodItem>> get() = _addedFoodItems
    
    private val _addedEquipmentItems = MutableStateFlow<List<AddedEquipmentItem>>(emptyList())
    val addedEquipmentItems: StateFlow<List<AddedEquipmentItem>> get() = _addedEquipmentItems

    fun addConsumed(calories: Int) {
        _consumed.value += calories
    }

    fun addBurned(calories: Int) {
        _burned.value += calories
    }
    
    fun removeConsumed(calories: Int) {
        _consumed.value = (_consumed.value - calories).coerceAtLeast(0)
    }
    
    fun removeBurned(calories: Int) {
        _burned.value = (_burned.value - calories).coerceAtLeast(0)
    }
    
    fun addFoodItem(name: String, calories: Int, imageUrl: String) {
        val newItem = AddedFoodItem(
            id = System.currentTimeMillis().toString(),
            name = name,
            calories = calories,
            imageUrl = imageUrl,
            timestamp = System.currentTimeMillis()
        )
        _addedFoodItems.value = _addedFoodItems.value + newItem
    }
    
    fun addEquipmentItem(name: String, caloriesBurned: Int, imageUrl: String, exerciseMinutes: Int) {
        val newItem = AddedEquipmentItem(
            id = System.currentTimeMillis().toString(),
            name = name,
            caloriesBurned = caloriesBurned,
            imageUrl = imageUrl,
            exerciseMinutes = exerciseMinutes,
            timestamp = System.currentTimeMillis()
        )
        _addedEquipmentItems.value = _addedEquipmentItems.value + newItem
    }
    
    fun removeFoodItem(itemId: String) {
        val item = _addedFoodItems.value.find { it.id == itemId }
        if (item != null) {
            removeConsumed(item.calories)
            _addedFoodItems.value = _addedFoodItems.value.filter { it.id != itemId }
        }
    }
    
    fun removeEquipmentItem(itemId: String) {
        val item = _addedEquipmentItems.value.find { it.id == itemId }
        if (item != null) {
            removeBurned(item.caloriesBurned)
            _addedEquipmentItems.value = _addedEquipmentItems.value.filter { it.id != itemId }
        }
    }
    
    fun setExerciseTimeMinutes(minutes: Int) {
        _exerciseTimeMinutes.value = minutes.coerceIn(1, 180) // Limit between 1 and 180 minutes
    }

    fun resetSession() {
        _consumed.value = 0
        _burned.value = 0
        _addedFoodItems.value = emptyList()
        _addedEquipmentItems.value = emptyList()
    }
}

data class AddedFoodItem(
    val id: String,
    val name: String,
    val calories: Int,
    val imageUrl: String,
    val timestamp: Long
)

data class AddedEquipmentItem(
    val id: String,
    val name: String,
    val caloriesBurned: Int,
    val imageUrl: String,
    val exerciseMinutes: Int,
    val timestamp: Long
)
