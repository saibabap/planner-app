package com.example.plannerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.plannerapp.ui.theme.PlannerappTheme
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlannerappTheme {

                val navController = rememberNavController()

                AppNavigation(navController)
            }
        }
    }
}