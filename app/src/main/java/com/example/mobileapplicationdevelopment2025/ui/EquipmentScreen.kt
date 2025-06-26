package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobileapplicationdevelopment2025.ui.components.EquipmentListItem
import com.example.mobileapplicationdevelopment2025.ui.components.AddedEquipmentItemCard
import com.example.mobileapplicationdevelopment2025.viewmodel.EquipmentViewModel

@Composable
fun EquipmentScreen(
    viewModel: EquipmentViewModel = hiltViewModel()
) {
    val equipment by viewModel.equipment.collectAsState()
    val burned by viewModel.burned.collectAsState()
    val addedEquipmentItems by viewModel.addedEquipmentItems.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Equipment — Burned: $burned kcal",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Show added equipment items section first if there are any
            if (addedEquipmentItems.isNotEmpty()) {
                item {
                    Text(
                        text = "Added Equipment (${addedEquipmentItems.size})",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                
                items(addedEquipmentItems) { addedItem ->
                    AddedEquipmentItemCard(
                        item = addedItem,
                        onRemove = { viewModel.removeEquipment(addedItem.id) }
                    )
                }
                
                item {
                    Divider(
                        modifier = Modifier.padding(vertical = 16.dp),
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = "Available Equipment",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
            
            // Equipment list
            items(equipment) { item ->
                EquipmentListItem(item = item) {
                    viewModel.addEquipment(item)
                }
            }
        }
    }
}
