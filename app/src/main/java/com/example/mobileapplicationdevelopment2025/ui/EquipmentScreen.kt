package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobileapplicationdevelopment2025.viewmodel.EquipmentViewModel
import kotlinx.coroutines.launch
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EquipmentScreen(viewModel: EquipmentViewModel = hiltViewModel()) {
    val equipmentList by viewModel.items.collectAsState()
    val added by viewModel.added.collectAsState()
    val total by viewModel.total.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(title = { Text("Equipment • ${total.toInt()} kcal") })
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(equipmentList) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    GlideImage(
                        model = viewModel.img(item.name),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(item.name)
                        added[item.name]?.let { Text("Added $it") }
                    }
                    IconButton(onClick = {
                        viewModel.add(item.name)
                        scope.launch {
                            snackbarHostState.showSnackbar("Added ${item.name}")
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
