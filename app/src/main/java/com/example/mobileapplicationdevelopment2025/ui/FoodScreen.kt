package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.mobileapplicationdevelopment2025.viewmodel.FoodViewModel

@Composable
fun FoodScreen(navController: NavHostController, vm: FoodViewModel = hiltViewModel()) {
    val state = vm.state.collectAsState()
    LazyColumn {
        items(state.value) {
            ItemCard(
                imageUrl = it.imageUrl,
                title = it.name,
                subtitle = "${it.calories} kcal",
                description = ""
            )
        }
    }
}
