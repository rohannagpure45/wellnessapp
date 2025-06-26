package com.example.mobileapplicationdevelopment2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import dagger.hilt.android.AndroidEntryPoint
import com.example.mobileapplicationdevelopment2025.ui.FoodScreen
import com.example.mobileapplicationdevelopment2025.ui.EquipmentScreen
import com.example.mobileapplicationdevelopment2025.ui.ProfileScreen
import com.example.mobileapplicationdevelopment2025.ui.SettingsScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { WellnessNavHost() }
    }
}

sealed class Screen(val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val label: String) {
    object Food : Screen("food", Icons.Filled.Restaurant, "Food")
    object Equipment : Screen("equipment", Icons.Filled.FitnessCenter, "Equipment")
    object Profile : Screen("profile", Icons.Filled.Person, "Profile")
    object Settings : Screen("settings", Icons.Filled.Settings, "Settings")
}

@Composable
fun WellnessNavHost() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            val currentRoute = navController
                .currentBackStackEntryAsState().value?.destination?.route
            NavigationBar {
                listOf(
                    Screen.Food,
                    Screen.Equipment,
                    Screen.Profile,
                    Screen.Settings
                ).forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Food.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Food.route) { FoodScreen() }
            composable(Screen.Equipment.route) { EquipmentScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(Screen.Settings.route) { SettingsScreen() }
        }
    }
}
