package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobileapplicationdevelopment2025.viewmodel.EquipmentViewModel
import com.example.mobileapplicationdevelopment2025.viewmodel.FoodViewModel

@Composable
fun ProfileScreen(
    foodVm: FoodViewModel = hiltViewModel(),
    eqVm: EquipmentViewModel = hiltViewModel()
) {
    val consumed by foodVm.totalKcal.collectAsState()
    val burned  by eqVm.totalBurn.collectAsState()
    val net     = consumed - burned

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("User Profile", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))
            Text("Name: Rohan Nagpure")
            Text("Email: nagpure.r@northeastern.edu")
            Spacer(Modifier.height(24.dp))
            Text("Consumed: $consumed kcal")
            Text("Burned:   $burned kcal")
            Text("Net:      $net kcal")
        }
    }
}
