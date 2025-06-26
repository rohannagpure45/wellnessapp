package com.example.mobileapplicationdevelopment2025.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import coil.compose.AsyncImage
import com.example.mobileapplicationdevelopment2025.data.remote.model.EquipmentDto
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobileapplicationdevelopment2025.viewmodel.EquipmentViewModel
import kotlin.math.roundToInt

@Composable
fun EquipmentListItem(
    item: EquipmentDto,
    onAdd: () -> Unit
) {
    // Get current exercise time settings to show actual calorie burn
    val equipmentViewModel: EquipmentViewModel = hiltViewModel()
    val exerciseTimeMultiplier by equipmentViewModel.exerciseTimeMultiplier.collectAsState()
    val exerciseTimeMinutes by equipmentViewModel.exerciseTimeMinutes.collectAsState()
    
    val actualCaloriesBurned = (item.caloriesBurnedPerHour * exerciseTimeMultiplier).roundToInt()
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "${item.caloriesBurnedPerHour.toInt()} kcal/h",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "$actualCaloriesBurned kcal ($exerciseTimeMinutes min)",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                )
            }
            IconButton(
                onClick = onAdd,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(8.dp)
                    )
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add Equipment",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
