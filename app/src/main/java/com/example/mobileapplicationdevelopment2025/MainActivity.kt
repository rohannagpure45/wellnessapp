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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.work.*
import com.example.mobileapplicationdevelopment2025.ui.components.BottomBar
import com.example.mobileapplicationdevelopment2025.ui.*
import com.example.mobileapplicationdevelopment2025.work.RefreshDataWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .build()

        val refreshRequest =
            PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "RefreshDataWork",
            ExistingPeriodicWorkPolicy.KEEP,
            refreshRequest
        )

        setContent {
            val navController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route
            Scaffold(
                bottomBar = {
                    if (currentRoute in listOf("food", "equipment", "profile")) {
                        BottomBar(navController)
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController,
                    startDestination = "login",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable("login")       { LoginScreen(navController) }
                    composable("food")        { FoodScreen(navController) }
                    composable("equipment")   { EquipmentScreen(navController) }
                    composable("profile")     { ProfileScreen(navController) }
                    composable("detail") {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.get<com.example.mobileapplicationdevelopment2025.data.WellnessItem>("item")
                            ?.let { DetailScreen(it) }
                    }
                }
            }
        }
    }
}
