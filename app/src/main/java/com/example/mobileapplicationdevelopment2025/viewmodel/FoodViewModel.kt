package com.example.mobileapplicationdevelopment2025.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapplicationdevelopment2025.data.remote.model.FoodDto
import com.example.mobileapplicationdevelopment2025.data.remote.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repo: FoodRepository
) : ViewModel() {

    private val _foods = MutableStateFlow<List<FoodDto>>(emptyList())
    val foods: StateFlow<List<FoodDto>> = _foods
    val totalKcal: StateFlow<Int> = foods
        .map { it.sumOf { f -> f.calories.toInt() } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)


    init {
        viewModelScope.launch {
            val presets = listOf("apple","egg","banana","bread","broccoli")
            _foods.value = presets.flatMap { repo.search(it) }
        }
    }
    private val defaults = listOf("apple","egg","banana","bread","broccoli")
    init { load(defaults) }
    fun refresh() = load(defaults)
    private fun load(terms: List<String>) = viewModelScope.launch {
        _foods.value = terms.flatMap { repo.search(it) }
    }
    fun search(q: String) = load(listOf(q))



}
