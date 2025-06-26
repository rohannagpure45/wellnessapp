package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobileapplicationdevelopment2025.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val consumed by viewModel.consumed.collectAsState(initial = 0)
    val burned by viewModel.burned.collectAsState(initial = 0)
    val net by viewModel.net.collectAsState(initial = 0)
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Username: admin", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(4.dp))
        Text("Consumed: $consumed kcal", style = MaterialTheme.typography.bodyMedium)
        Text("Burned: $burned kcal", style = MaterialTheme.typography.bodyMedium)
        Text("Net: $net kcal", style = MaterialTheme.typography.bodyMedium)
    }
}
