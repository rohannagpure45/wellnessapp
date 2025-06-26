package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.mobileapplicationdevelopment2025.viewmodel.FoodViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun FoodScreen(viewModel: FoodViewModel = hiltViewModel()) {
    val results by viewModel.searchResults.collectAsState()
    val total   by viewModel.totalKcal.collectAsState()
    val snackHost = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var query by remember { mutableStateOf("") }

    Scaffold(
        snackbarHost = { SnackbarHost(snackHost) },
        topBar = {
            TopAppBar(
                title = { Text("Food — Total: $total kcal") },
                actions = {
                    IconButton(onClick = { viewModel.refresh() }) {
                        Icon(Icons.Default.Refresh, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    placeholder = { Text("Search food") }
                )
                Spacer(Modifier.width(8.dp))
                Button(onClick = {
                    if (query.isNotBlank()) {
                        viewModel.search(query)
                        scope.launch {
                            snackHost.showSnackbar("Searched for \"$query\"")
                        }
                    }
                }) {
                    Text("Search")
                }
            }
            Divider()
            LazyColumn(Modifier.fillMaxSize()) {
                if (results.isEmpty()) {
                    item {
                        Text(
                            "No items—try a different search or pull to refresh.",
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                items(results) { item ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GlideImage(
                            model = "https://source.unsplash.com/100x100/?${item.name}",
                            contentDescription = null,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text(item.name, style = MaterialTheme.typography.titleMedium)
                            Text("Calories ${item.calories}", style = MaterialTheme.typography.bodySmall)
                        }
                        IconButton(onClick = {
                            viewModel.addFood(item)
                            scope.launch {
                                snackHost.showSnackbar("Added ${item.name}")
                            }
                        }) {
                            Icon(Icons.Default.Add, contentDescription = null)
                        }
                    }
                    Divider()
                }
            }
        }
    }
}
