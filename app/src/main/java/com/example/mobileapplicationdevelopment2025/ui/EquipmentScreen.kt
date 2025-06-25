package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mobileapplicationdevelopment2025.ui.components.ItemCard

@Composable
fun EquipmentScreen(
    navController: NavHostController,
    viewModel: EquipmentViewModel = hiltViewModel()
) {
    val list by viewModel.uiState.collectAsState()

    LazyColumn {
        items(list) { item ->
            ItemCard(
                imageUrl   = item.imageUrl,
                title      = item.name,
                subtitle   = item.category,
                description = item.description
            )
            ItemCard(imageUrl = item.imageUrl, title = item.name, subtitle   = "", description = item.description )
        }
    }
}
