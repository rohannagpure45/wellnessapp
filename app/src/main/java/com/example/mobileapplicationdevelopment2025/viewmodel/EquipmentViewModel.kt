package com.example.mobileapplicationdevelopment2025.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapplicationdevelopment2025.data.remote.model.EquipmentDto
import com.example.mobileapplicationdevelopment2025.data.remote.repository.EquipmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.math.max
import javax.inject.Inject

@HiltViewModel
class EquipmentViewModel @Inject constructor(
    private val repo: EquipmentRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<EquipmentDto>>(emptyList())
    val items: StateFlow<List<EquipmentDto>> = _items.asStateFlow()

    private val _added = MutableStateFlow<Map<String, Int>>(emptyMap())
    val added: StateFlow<Map<String, Int>> = _added.asStateFlow()

    val totalBurn: StateFlow<Int> = combine(_items, _added) { list, counts ->
        list.sumOf { eq ->
            val c = counts[eq.name] ?: 0
            (eq.caloriesBurnedPerHour * c).toInt()
        }
    }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    init {
        viewModelScope.launch {
            _items.value = repo.refresh()
        }
    }

    fun add(name: String) {
        _added.update { m ->
            val c = m[name] ?: 0
            m + (name to (c + 1))
        }
    }

    fun remove(name: String) {
        _added.update { m ->
            val c = m[name] ?: 0
            m + (name to max(0, c - 1))
        }
    }
}
