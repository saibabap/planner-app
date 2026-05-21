package com.example.plannerapp

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

  
    val scale = remember {

        Animatable(0.5f)
    }

 
    LaunchedEffect(Unit) {

        scale.animateTo(

            targetValue = 1f,

            animationSpec =
                tween(
                    durationMillis = 1500,
                    easing = FastOutSlowInEasing
                )
        )

        delay(2500)

        navController.navigate("home") {

            popUpTo("splash") {
                inclusive = true
            }
        }
    }

    val gradient = Brush.verticalGradient(

        colors = listOf(

            Color(0xFF0A0A0A),

            Color(0xFF1A1A1A),

            Color(0xFF121212)
        )
    )

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(gradient),

        contentAlignment =
            Alignment.Center
    ) {

        Column(

            horizontalAlignment =
                Alignment.CenterHorizontally

        ) {

          
            Icon(

                imageVector =
                    Icons.Default.TaskAlt,

                contentDescription =
                    null,

                tint =
                    Color(0xFFFF2D55),

                modifier = Modifier
                    .size(100.dp)
                    .scale(scale.value)
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

           
            Text(

                text =
                    "Smart Daily Planner Pro",

                color = Color.White,

                fontSize = 32.sp,

                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            // Subtitle
            Text(

                text =
                    "Plan • Study • Sleep • Achieve",

                color =
                    Color.LightGray,

                fontSize = 16.sp
            )

            Spacer(
                modifier =
                    Modifier.height(40.dp)
            )

            // Loading Text
            Text(

                text = "Loading...",

                color =
                    Color.Gray,

                fontSize = 14.sp
            )
        }
    }
}
