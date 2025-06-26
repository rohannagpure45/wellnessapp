package com.example.wellnessapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mobileapplicationdevelopment2025.data.remote.model.FoodData
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

@Composable
fun FoodListItem(
    food: FoodData,
    onAdd: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        AsyncImage(
            model = food.imageUrl,
            contentDescription = food.name,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = food.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Calories: ${food.calories}", style = MaterialTheme.typography.bodySmall)
        }
        IconButton(onClick = onAdd) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Food")
        }
    }
}
