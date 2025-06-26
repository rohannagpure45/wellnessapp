package com.example.mobileapplicationdevelopment2025.data

import kotlinx.coroutines.flow.MutableStateFlow

object CalorieTracker {
    val consumed = MutableStateFlow(0.0)
    val burned   = MutableStateFlow(0.0)
}
