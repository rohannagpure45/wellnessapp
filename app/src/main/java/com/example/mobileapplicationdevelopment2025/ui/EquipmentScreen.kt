package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.mobileapplicationdevelopment2025.viewmodel.EquipmentViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun EquipmentScreen(viewModel: EquipmentViewModel = hiltViewModel()) {
    val items by viewModel.items.collectAsState()
    val added by viewModel.added.collectAsState()
    val totalBurn by viewModel.totalBurn.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text("Equipment — Burned: $totalBurn kcal") })
    }) { padding ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(items) { eq ->
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GlideImage(
                            model = eq.imageUrl,
                            contentDescription = eq.name,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text(eq.name, style = MaterialTheme.typography.titleMedium)
                            Text(eq.category, style = MaterialTheme.typography.bodySmall)
                            Text(eq.description, style = MaterialTheme.typography.bodySmall)
                            Text("Burn/hr: ${eq.caloriesBurnedPerHour}", style = MaterialTheme.typography.bodySmall)
                        }
                        Spacer(Modifier.width(8.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Added: ${added[eq.name] ?: 0}", style = MaterialTheme.typography.bodySmall)
                            Row {
                                IconButton(onClick = { viewModel.remove(eq.name) }) {
                                    Icon(Icons.Default.Remove, contentDescription = null)
                                }
                                IconButton(onClick = { viewModel.add(eq.name) }) {
                                    Icon(Icons.Default.Add, contentDescription = null)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
