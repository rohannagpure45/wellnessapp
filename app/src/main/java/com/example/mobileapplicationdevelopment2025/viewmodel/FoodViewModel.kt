package com.example.mobileapplicationdevelopment2025.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapplicationdevelopment2025.data.CalorieTracker
import com.example.mobileapplicationdevelopment2025.data.remote.model.FoodDto
import com.example.mobileapplicationdevelopment2025.data.remote.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repo: FoodRepository
) : ViewModel() {

    private val _list = MutableStateFlow<List<FoodDto>>(emptyList())
    val list: StateFlow<List<FoodDto>> = _list

    private val _added = MutableStateFlow<Map<String, Int>>(emptyMap())
    val added: StateFlow<Map<String, Int>> = _added

    val total: StateFlow<Double> = combine(_list, _added) { food, cart ->
        cart.entries.sumOf { (n, q) ->
            food.firstOrNull { it.name == n }?.calories?.times(q) ?: 0.0
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)

    init { presets() }

    private fun presets() = viewModelScope.launch {
        _list.value = listOf("apple","egg","banana","bread","broccoli")
            .flatMap { repo.search(it) }
    }

    fun search(q: String) = viewModelScope.launch {
        val result = runCatching { repo.search(q) }.getOrDefault(emptyList())
        if (result.isEmpty()) {
            _list.value = listOf(FoodDto("apple",0.0,0.0,0.0,0.0,0.0))
        } else {
            _list.value = result
        }
    }


    fun add(item: FoodDto) {
        _added.value = _added.value.toMutableMap().apply {
            put(item.name, (get(item.name) ?: 0) + 1)
        }
        CalorieTracker.consumed.value += item.calories
    }

    fun img(name: String): String =
        "https://picsum.photos/seed/${name.hashCode()}/100/100"
}
