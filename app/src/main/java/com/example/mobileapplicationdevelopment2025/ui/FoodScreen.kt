package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobileapplicationdevelopment2025.ui.components.ItemCard
import com.example.mobileapplicationdevelopment2025.viewmodel.FoodViewModel


@Composable
fun FoodScreen(
    viewModel: FoodViewModel = hiltViewModel()
) {
    val foodList by viewModel.foods.collectAsState()

    if (foodList.isEmpty()) {
        Text(
            text = "No food items. Pull-to-refresh or try a new search.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxSize()
        )
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(foodList) { food ->
                ItemCard(
                    imageUrl    = food.imageUrl,
                    title       = food.name,
                    subtitle    = "${food.calories} kcal",
                    description = ""
                )
            }
        }
    }
}
