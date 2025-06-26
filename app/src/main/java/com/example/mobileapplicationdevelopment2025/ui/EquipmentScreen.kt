package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobileapplicationdevelopment2025.ui.components.EquipmentListItem
import com.example.mobileapplicationdevelopment2025.viewmodel.EquipmentViewModel

@Composable
fun EquipmentScreen(
    viewModel: EquipmentViewModel = hiltViewModel()
) {
    val equipment by viewModel.equipment.collectAsState()
    val burned by viewModel.burned.collectAsState()

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
            items(equipment) { item ->
                EquipmentListItem(item = item) {
                    viewModel.addEquipment(item)
                }
            }
        }
    }
}
