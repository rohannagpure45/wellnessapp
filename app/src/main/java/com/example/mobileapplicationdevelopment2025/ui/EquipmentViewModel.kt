package com.example.mobileapplicationdevelopment2025.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapplicationdevelopment2025.data.remote.repository.EquipmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class EquipmentViewModel @Inject constructor(
    private val repository: EquipmentRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            repository.refresh()
        }
    }

    val uiState = repository
        .getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun refresh() = viewModelScope.launch { repository.refresh() }
}
