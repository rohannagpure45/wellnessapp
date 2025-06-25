package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.MaterialTheme
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("User Profile", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Name: [Your Name]")
        Text("Email: [you@example.com]")
        Spacer(modifier = Modifier.height(8.dp))
        Text("This is a placeholder profile screen for the WellnessApp.")
    }
}
