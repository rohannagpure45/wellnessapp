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

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repo: FoodRepository
) : ViewModel() {

    private val _foods = MutableStateFlow<List<FoodDto>>(emptyList())
    val foods: StateFlow<List<FoodDto>> = _foods

    fun search(q: String) = viewModelScope.launch {
        _foods.value = repo.search(q)
    }

    fun refresh() = viewModelScope.launch {
        repo.refresh()
    }
}
