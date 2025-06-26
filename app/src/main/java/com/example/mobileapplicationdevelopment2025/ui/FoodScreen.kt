package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mobileapplicationdevelopment2025.viewmodel.FoodViewModel
import kotlinx.coroutines.launch
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(viewModel: FoodViewModel = hiltViewModel()) {
    val foods by viewModel.list.collectAsState()
    val added by viewModel.added.collectAsState()
    val total by viewModel.total.collectAsState()
    val snack = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var query by remember { mutableStateOf("") }

    Scaffold(
        snackbarHost = { SnackbarHost(snack) },
        topBar = {
            TopAppBar(
                title = { Text("Food • ${total.toInt()} kcal") },
                actions = {
                    IconButton(onClick = { viewModel.search(query.ifBlank { "apple" }) }) {
                        Icon(Icons.Default.Refresh, null)
                    }
                }
            )
        }
    ) { inner ->
        Column(Modifier.fillMaxSize().padding(inner)) {
            Row(Modifier.fillMaxWidth().padding(12.dp)) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    placeholder = { Text("Search food") }
                )
                Spacer(Modifier.width(8.dp))
                Button(onClick = { if (query.isNotBlank()) viewModel.search(query) }) { Text("Search") }
            }
            Divider()
            LazyColumn {
                items(foods) { item ->
                    Row(Modifier.fillMaxWidth().padding(12.dp)) {
                        GlideImage(
                            model = viewModel.img(item.name),
                            contentDescription = null,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text(item.name, style = MaterialTheme.typography.titleMedium)
                            Text("Calories ${item.calories}")
                            added[item.name]?.let { Text("Added $it") }
                        }
                        IconButton(onClick = {
                            viewModel.add(item)
                            scope.launch { snack.showSnackbar("Added ${item.name}") }
                        }) { Icon(Icons.Default.Add, null) }
                    }
                    Divider()
                }
            }
        }
    }
}
