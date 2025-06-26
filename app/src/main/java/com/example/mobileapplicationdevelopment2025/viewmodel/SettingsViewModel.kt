package com.example.mobileapplicationdevelopment2025.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import com.example.mobileapplicationdevelopment2025.session.SessionManager
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {
    
    val exerciseTimeMinutes: StateFlow<Int> = sessionManager.exerciseTimeMinutes
    val exerciseTimeMultiplier: StateFlow<Float> = sessionManager.exerciseTimeMultiplier
    
    fun setExerciseTimeMinutes(minutes: Int) {
        sessionManager.setExerciseTimeMinutes(minutes)
    }
} 