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

        composable("splash") {

            SplashScreen(navController)
        }

        composable("home") {

            HomeScreen(navController)
        }

        composable("add") {

            AddTaskScreen(navController)
        }

        composable("study") {

            StudyScreen()
        }

        composable("sleep") {

            SleepScreen()
        }

        composable("reports") {

            ReportsScreen()
        }

        composable("deleted") {

            DeletedTasksScreen()
        }

        composable("settings") {

            SettingsScreen()
        }
    }
}
