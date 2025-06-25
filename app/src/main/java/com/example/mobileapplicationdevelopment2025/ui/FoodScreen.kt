package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mobileapplicationdevelopment2025.viewmodel.FoodViewModel

@Composable
fun FoodScreen(navController: NavHostController, viewModel: FoodViewModel = hiltViewModel())  {
    var query by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search food") }
        )
        Spacer(Modifier.height(8.dp))
        Button(onClick = { viewModel.search(query) }) { Text("Go") }

        val list by viewModel.foods.collectAsState()
        LazyColumn {
            items(list) { item ->
                Text(item.name, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
