package com.example.mobileapplicationdevelopment2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .build()
        val refreshRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "RefreshDataWork",
            ExistingPeriodicWorkPolicy.KEEP,
            refreshRequest
        )
        // Set up UI content with Jetpack Compose and Navigation
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            Scaffold(
                bottomBar = {
                    if (currentRoute in listOf("food", "equipment", "profile")) {
                        BottomBar(navController)
                    }
                }
            ) { innerPadding ->
                NavHost(navController, startDestination = "login", modifier = Modifier.padding(innerPadding)) {
                    composable("login") { LoginScreen(navController) }
                    composable("food") { FoodScreen(navController) }
                    composable("equipment") { EquipmentScreen(navController) }
                    composable("profile") { ProfileScreen(navController) }
                    composable("detail") {
                        val item = navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.get<WellnessItem>("item")
                        item?.let { DetailScreen(item = it) }
                    }
                }
            }
        }
    }
}
