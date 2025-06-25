package com.example.mobileapplicationdevelopment2025.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapplicationdevelopment2025.data.remote.model.FoodDto
import com.example.mobileapplicationdevelopment2025.data.remote.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel that exposes the food catalogue to the UI and lets the user search it.
 *
 * - Uses Hilt for DI (annotate with **@HiltViewModel**, *not* @Singleton).
 * - Exposes the repository’s Flow directly; Compose screens can
 *   `collectAsState()` on it.
 */
@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel() {

    val foods: Flow<List<FoodDto>> = repository.foods

    fun search(query: String) = viewModelScope.launch {
        repository.search(query)

    }

    fun refresh() = viewModelScope.launch {
        repository.refresh()
    }
}
