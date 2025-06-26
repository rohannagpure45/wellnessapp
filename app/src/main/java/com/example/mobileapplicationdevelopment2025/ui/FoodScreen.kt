package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobileapplicationdevelopment2025.ui.components.FoodListItem
import com.example.mobileapplicationdevelopment2025.ui.components.AddedFoodItemCard
import com.example.mobileapplicationdevelopment2025.viewmodel.FoodViewModel
import kotlinx.coroutines.launch

@Composable
fun FoodScreen(
    viewModel: FoodViewModel = hiltViewModel()
) {
    var query by remember { mutableStateOf("") }
    val foods by viewModel.foods.collectAsState()
    val total by viewModel.total.collectAsState()
    val addedFoodItems by viewModel.addedFoodItems.collectAsState()
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
            // Show added food items section first if there are any
            if (addedFoodItems.isNotEmpty()) {
                item {
                    Text(
                        text = "Added Food Items (${addedFoodItems.size})",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                
                items(addedFoodItems) { addedItem ->
                    AddedFoodItemCard(
                        item = addedItem,
                        onRemove = { viewModel.removeFood(addedItem.id) }
                    )
                }
                
                item {
                    Divider(
                        modifier = Modifier.padding(vertical = 16.dp),
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = "Search Results",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
            
            // Search results
            items(foods) { food ->
                FoodListItem(food = food) {
                    viewModel.addFood(food)
                }
            }
        }
    }
}
