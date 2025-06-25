package com.example.mobileapplicationdevelopment2025.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapplicationdevelopment2025.data.repository.EquipmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import javax.inject.Inject

@HiltViewModel
class EquipmentViewModel @Inject constructor(
    private val repo: EquipmentRepository
) : ViewModel() {
    val equipment: StateFlow<List<com.example.mobileapplicationdevelopment2025.data.local.EquipmentEntity>> =
        repo.equipment.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
}
