package com.example.mobileapplicationdevelopment2025.session

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    private val _consumed = MutableStateFlow(0)
    val consumed: StateFlow<Int> get() = _consumed

    private val _burned = MutableStateFlow(0)
    val burned: StateFlow<Int> get() = _burned

    fun addConsumed(calories: Int) {
        _consumed.value += calories
    }

    fun addBurned(calories: Int) {
        _burned.value += calories
    }

    fun resetSession() {
        _consumed.value = 0
        _burned.value = 0
    }
}
