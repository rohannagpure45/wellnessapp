package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobileapplicationdevelopment2025.ui.components.FoodListItem
import com.example.mobileapplicationdevelopment2025.viewmodel.FoodViewModel
import kotlinx.coroutines.launch

@Composable
fun FoodScreen(
    viewModel: FoodViewModel = hiltViewModel()
) {
    var query by remember { mutableStateOf("") }
    val foods by viewModel.foods.collectAsState()
    val total by viewModel.total.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.searchFood("apple")        // initial load with sample food
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("Search food") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                scope.launch { 
                    val searchQuery = if (query.isBlank()) "apple" else query
                    viewModel.searchFood(searchQuery) 
                }
            }) {
                Text("Search")
            }
        }

        Text(
            text = "Food — Total: $total kcal",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(foods) { food ->
                FoodListItem(food = food) {
                    viewModel.addFood(food)
                }
            }
        }
    }
}
