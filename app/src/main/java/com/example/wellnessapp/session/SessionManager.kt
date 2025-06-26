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

    fun addConsumed(calories: Int) {
        _consumed.value += calories
    }

    fun addBurned(calories: Int) {
        _burned.value += calories
    }
    
    fun setExerciseTimeMinutes(minutes: Int) {
        _exerciseTimeMinutes.value = minutes.coerceIn(1, 180) // Limit between 1 and 180 minutes
    }

    fun resetSession() {
        _consumed.value = 0
        _burned.value = 0
    }
}
