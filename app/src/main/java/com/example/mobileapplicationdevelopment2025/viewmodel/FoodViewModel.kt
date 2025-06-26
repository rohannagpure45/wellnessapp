package com.example.mobileapplicationdevelopment2025.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapplicationdevelopment2025.data.remote.model.FoodDto
import com.example.mobileapplicationdevelopment2025.data.remote.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repo: FoodRepository
) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<FoodDto>>(emptyList())
    val searchResults: StateFlow<List<FoodDto>> = _searchResults

    private val _selectedFoods = MutableStateFlow<List<FoodDto>>(emptyList())
    val selectedFoods: StateFlow<List<FoodDto>> = _selectedFoods

    val totalKcal: StateFlow<Int> = _selectedFoods
        .map { it.sumOf { food -> food.calories.toInt() } }
        .stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.Eagerly, 0)

    init {
        viewModelScope.launch {
            val presets = listOf("apple","egg","banana","bread","broccoli")
            _searchResults.value = presets.flatMap { repo.search(it) }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            val presets = listOf("apple","egg","banana","bread","broccoli")
            _searchResults.value = presets.flatMap { repo.search(it) }
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            _searchResults.value = repo.search(query)
        }
    }

    fun addFood(item: FoodDto) {
        _selectedFoods.update { it + item }
    }
}
