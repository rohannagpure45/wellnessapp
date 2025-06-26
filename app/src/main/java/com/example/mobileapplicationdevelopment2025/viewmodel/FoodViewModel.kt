package com.example.mobileapplicationdevelopment2025.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.mobileapplicationdevelopment2025.data.remote.model.FoodDto
import com.example.mobileapplicationdevelopment2025.data.remote.repository.FoodRepository
import com.example.mobileapplicationdevelopment2025.session.SessionManager
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repository: FoodRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _foods = MutableStateFlow<List<FoodDto>>(emptyList())
    val foods: StateFlow<List<FoodDto>> get() = _foods

    private val _total = MutableStateFlow(0)
    val total: StateFlow<Int> get() = _total

    fun searchFood(query: String) {
        viewModelScope.launch {
            _foods.value = repository.search(query)
        }
    }

    fun addFood(food: FoodDto) {
        val c = food.calories.toInt()
        sessionManager.addConsumed(c)
        _total.value += c
    }
}
