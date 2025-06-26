
package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobileapplicationdevelopment2025.data.CalorieTracker
import kotlinx.coroutines.flow.combine

@Composable
fun ProfileScreen() {
    val consumed by CalorieTracker.consumed.collectAsState()
    val burned   by CalorieTracker.burned.collectAsState()
    val net by remember(consumed, burned) { mutableStateOf(consumed - burned) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("User Profile", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Name: Rohan Nagpure")
        Text("Email: nagpure.r@northeastern.edu")
        Spacer(modifier = Modifier.height(10.dp))

        Text("Consumed: ${consumed.toInt()} kcal", modifier = Modifier.padding(4.dp))
        Text("Burned:   ${burned.toInt()} kcal", modifier = Modifier.padding(4.dp))
        Text("Net:      ${net.toInt()} kcal", modifier = Modifier.padding(4.dp))
    }
}
