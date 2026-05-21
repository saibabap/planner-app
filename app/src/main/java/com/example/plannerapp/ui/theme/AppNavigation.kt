package com.example.plannerapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(
    navController: NavHostController
) {

    NavHost(

        navController = navController,

        startDestination = "splash"
    ) {

        // Splash Screen
        composable("splash") {

            SplashScreen(navController)
        }

        // Home Screen
        composable("home") {

            HomeScreen(navController)
        }

        // Add Task Screen
        composable("add") {

            AddTaskScreen(navController)
        }

        // Study Screen
        composable("study") {

            StudyScreen()
        }

        // Sleep Screen
        composable("sleep") {

            SleepScreen()
        }

        // Reports Screen
        composable("reports") {

            ReportsScreen()
        }

        // Deleted Tasks Screen
        composable("deleted") {

            DeletedTasksScreen()
        }

        // Settings Screen
        composable("settings") {

            SettingsScreen()
        }
    }
}