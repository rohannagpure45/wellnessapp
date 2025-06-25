package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobileapplicationdevelopment2025.ui.components.ItemCard

@Composable
fun EquipmentScreen(
    viewModel: EquipmentViewModel = hiltViewModel()
) {
    val list by viewModel.uiState.collectAsState()

    LazyColumn {
        items(list) { item ->
            ItemCard(
                imageUrl    = item.imageUrl,
                title       = item.name,
                subtitle    = item.category,
                description = item.description
            )
        }
    }
}
