package com.example.mobileapplicationdevelopment2025.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import com.example.mobileapplicationdevelopment2025.session.SessionManager
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    sessionManager: SessionManager
) : ViewModel() {
    val consumed: StateFlow<Int> = sessionManager.consumed
    val burned: StateFlow<Int> = sessionManager.burned
    val net: StateFlow<Int> = combine(consumed, burned) { c, b -> c - b }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)
}
