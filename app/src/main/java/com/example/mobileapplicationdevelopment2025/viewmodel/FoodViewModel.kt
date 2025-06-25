package com.example.mobileapplicationdevelopment2025.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapplicationdevelopment2025.repository.FoodRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FoodViewModel(
    private val repo: FoodRepository
) : ViewModel() {

    val food = repo.observeFood()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    init { viewModelScope.launch { repo.ensureSeeded() } }
}
