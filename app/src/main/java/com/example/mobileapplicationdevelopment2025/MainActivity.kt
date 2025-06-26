package com.example.mobileapplicationdevelopment2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobileapplicationdevelopment2025.ui.*
import com.example.mobileapplicationdevelopment2025.ui.components.BottomBar
import com.example.mobileapplicationdevelopment2025.work.RefreshWorker
import dagger.hilt.android.AndroidEntryPoint
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var authed by remember { mutableStateOf(false) }
            val rootNav = rememberNavController()

            val req = PeriodicWorkRequestBuilder<RefreshWorker>(12, java.util.concurrent.TimeUnit.HOURS)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.UNMETERED)
                        .setRequiresBatteryNotLow(true)
                        .build()
                )
                .build()

          //  WorkManager.getInstance(this)
              //  .enqueueUniquePeriodicWork("refresh", ExistingPeriodicWorkPolicy.KEEP, req)
            NavHost(
                navController = rootNav,
                startDestination = if (authed) "main" else "login"
            ) {
                composable("login") {
                    LoginScreen(
                        onSuccess = { authed = true ; rootNav.navigate("main") { popUpTo("login") { inclusive = true } } }
                    )
                }

                composable("main") {
                    val tabNav = rememberNavController()
                    val showBar by derivedStateOf {
                        tabNav.currentDestination?.route in listOf("food","equipment","profile")
                    }
                    Scaffold(
                        bottomBar = { if (showBar) BottomBar(tabNav) }
                    ) { inner ->
                        NavHost(
                            navController = tabNav,
                            startDestination = "food",
                            modifier = Modifier.padding(inner)
                        ) {
                            composable("food")      { FoodScreen() }
                            composable("equipment") { EquipmentScreen() }
                            composable("profile")   { ProfileScreen() }
                        }
                    }
                }
            }
        }
    }
}
