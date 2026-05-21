package com.example.plannerapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen() {

    val backgroundColor =
        Color(0xFF0A0A0A)

    val cardColor =
        Color(0xFF1A1A1A)

    val pinkColor =
        Color(0xFFFF2D55)

    var darkMode by remember {

        mutableStateOf(true)
    }

    var notifications by remember {

        mutableStateOf(true)
    }

    var vibration by remember {

        mutableStateOf(true)
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(
                backgroundColor
            )
            .padding(20.dp)
            .verticalScroll(
                rememberScrollState()
            )

    ) {

        // Title
        Text(

            text = "Settings",

            color = Color.White,

            fontSize = 30.sp,

            fontWeight =
                FontWeight.Bold
        )

        Spacer(
            modifier =
                Modifier.height(25.dp)
        )

        // Dark Mode
        SettingsSwitchCard(

            icon = {

                Icon(

                    imageVector =
                        Icons.Default.DarkMode,

                    contentDescription =
                        null,

                    tint = pinkColor
                )
            },

            title = "Dark Mode",

            checked = darkMode,

            onCheckedChange = {

                darkMode = it
            },

            cardColor = cardColor
        )

        Spacer(
            modifier =
                Modifier.height(15.dp)
        )

        // Notifications
        SettingsSwitchCard(

            icon = {

                Icon(

                    imageVector =
                        Icons.Default.Notifications,

                    contentDescription =
                        null,

                    tint = pinkColor
                )
            },

            title = "Notifications",

            checked = notifications,

            onCheckedChange = {

                notifications = it
            },

            cardColor = cardColor
        )

        Spacer(
            modifier =
                Modifier.height(15.dp)
        )

        // Vibration
        SettingsSwitchCard(

            icon = {

                Icon(

                    imageVector =
                        Icons.Default.Notifications,

                    contentDescription =
                        null,

                    tint = pinkColor
                )
            },

            title = "Vibration",

            checked = vibration,

            onCheckedChange = {

                vibration = it
            },

            cardColor = cardColor
        )

        Spacer(
            modifier =
                Modifier.height(25.dp)
        )

        // App Lock
        SettingsButtonCard(

            icon = {

                Icon(

                    imageVector =
                        Icons.Default.Lock,

                    contentDescription =
                        null,

                    tint = pinkColor
                )
            },

            title = "App Lock",

            cardColor = cardColor
        )

        Spacer(
            modifier =
                Modifier.height(15.dp)
        )

        // Backup
        SettingsButtonCard(

            icon = {

                Icon(

                    imageVector =
                        Icons.Default.Backup,

                    contentDescription =
                        null,

                    tint = pinkColor
                )
            },

            title = "Backup & Restore",

            cardColor = cardColor
        )

        Spacer(
            modifier =
                Modifier.height(15.dp)
        )

        // Export PDF
        SettingsButtonCard(

            icon = {

                Icon(

                    imageVector =
                        Icons.Default.PictureAsPdf,

                    contentDescription =
                        null,

                    tint = pinkColor
                )
            },

            title = "Export Reports PDF",

            cardColor = cardColor
        )

        Spacer(
            modifier =
                Modifier.height(15.dp)
        )

        // Language
        SettingsButtonCard(

            icon = {

                Icon(

                    imageVector =
                        Icons.Default.Language,

                    contentDescription =
                        null,

                    tint = pinkColor
                )
            },

            title = "Language",

            cardColor = cardColor
        )

        Spacer(
            modifier =
                Modifier.height(15.dp)
        )

        // Theme Colors
        SettingsButtonCard(

            icon = {

                Icon(

                    imageVector =
                        Icons.Default.Palette,

                    contentDescription =
                        null,

                    tint = pinkColor
                )
            },

            title = "Theme Colors",

            cardColor = cardColor
        )

        Spacer(
            modifier =
                Modifier.height(15.dp)
        )

        // Storage
        SettingsButtonCard(

            icon = {

                Icon(

                    imageVector =
                        Icons.Default.Storage,

                    contentDescription =
                        null,

                    tint = pinkColor
                )
            },

            title = "Storage & Data",

            cardColor = cardColor
        )

        Spacer(
            modifier =
                Modifier.height(15.dp)
        )

        // Feedback
        SettingsButtonCard(

            icon = {

                Icon(

                    imageVector =
                        Icons.Default.Feedback,

                    contentDescription =
                        null,

                    tint = pinkColor
                )
            },

            title = "Feedback",

            cardColor = cardColor
        )

        Spacer(
            modifier =
                Modifier.height(15.dp)
        )

        // About
        SettingsButtonCard(

            icon = {

                Icon(

                    imageVector =
                        Icons.Default.Info,

                    contentDescription =
                        null,

                    tint = pinkColor
                )
            },

            title = "About App",

            cardColor = cardColor
        )

        Spacer(
            modifier =
                Modifier.height(30.dp)
        )

        // Version
        Text(

            text = "Version 1.0.0",

            color = Color.Gray,

            fontSize = 14.sp
        )

        Spacer(
            modifier =
                Modifier.height(20.dp)
        )
    }
}

// Switch Card
@Composable
fun SettingsSwitchCard(

    icon: @Composable () -> Unit,

    title: String,

    checked: Boolean,

    onCheckedChange: (Boolean) -> Unit,

    cardColor: Color
) {

    Card(

        colors =
            CardDefaults.cardColors(
                containerColor =
                    cardColor
            )

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Row(

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                icon()

                Spacer(
                    modifier =
                        Modifier.width(
                            12.dp
                        )
                )

                Text(

                    text = title,

                    color = Color.White,

                    fontSize = 18.sp
                )
            }

            Switch(

                checked = checked,

                onCheckedChange =
                    onCheckedChange
            )
        }
    }
}

// Button Card
@Composable
fun SettingsButtonCard(

    icon: @Composable () -> Unit,

    title: String,

    cardColor: Color
) {

    Card(

        colors =
            CardDefaults.cardColors(
                containerColor =
                    cardColor
            )

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            icon()

            Spacer(
                modifier =
                    Modifier.width(
                        12.dp
                    )
            )

            Text(

                text = title,

                color = Color.White,

                fontSize = 18.sp
            )
        }
    }
}