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
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.Vibration
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

    var soundEffects by remember {

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

        Text(

            text = "Settings",

            color = Color.White,

            fontSize = 32.sp,

            fontWeight =
                FontWeight.Bold
        )

        Spacer(
            modifier =
                Modifier.height(25.dp)
        )

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

        SettingsSwitchCard(

            icon = {

                Icon(

                    imageVector =
                        Icons.Default.Vibration,

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
                Modifier.height(15.dp)
        )

        SettingsSwitchCard(

            icon = {

                Icon(

                    imageVector =
                        Icons.Default.VolumeUp,

                    contentDescription =
                        null,

                    tint = pinkColor
                )
            },

            title = "Sound Effects",

            checked = soundEffects,

            onCheckedChange = {

                soundEffects = it
            },

            cardColor = cardColor
        )

        Spacer(
            modifier =
                Modifier.height(25.dp)
        )

        Text(

            text = "More Options",

            color = Color.White,

            fontSize = 24.sp,

            fontWeight =
                FontWeight.Bold
        )

        Spacer(
            modifier =
                Modifier.height(18.dp)
        )

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
                Modifier.height(35.dp)
        )

        Card(

            modifier = Modifier
                .fillMaxWidth(),

            colors =
                CardDefaults.cardColors(
                    containerColor =
                        cardColor
                ),

            shape =
                RoundedCornerShape(
                    22.dp
                )

        ) {

            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

            ) {

                Text(

                    text = "Smart Daily Planner",

                    color = Color.White,

                    fontSize = 24.sp,

                    fontWeight =
                        FontWeight.Bold
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                Text(

                    text =
                        "Plan your tasks, track sleep, manage study sessions and improve productivity with a modern planner experience.",

                    color = Color.LightGray,

                    fontSize = 16.sp,

                    lineHeight = 25.sp
                )

                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )

                Text(

                    text = "Version 1.0.0",

                    color = pinkColor,

                    fontSize = 15.sp
                )
            }
        }

        Spacer(
            modifier =
                Modifier.height(120.dp)
        )
    }
}

@Composable
fun SettingsSwitchCard(

    icon: @Composable () -> Unit,

    title: String,

    checked: Boolean,

    onCheckedChange: (Boolean) -> Unit,

    cardColor: Color
) {

    Card(

        shape =
            RoundedCornerShape(
                22.dp
            ),

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
                            14.dp
                        )
                )

                Text(

                    text = title,

                    color = Color.White,

                    fontSize = 18.sp,

                    fontWeight =
                        FontWeight.Medium
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

@Composable
fun SettingsButtonCard(

    icon: @Composable () -> Unit,

    title: String,

    cardColor: Color
) {

    Card(

        shape =
            RoundedCornerShape(
                22.dp
            ),

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
                        14.dp
                    )
            )

            Text(

                text = title,

                color = Color.White,

                fontSize = 18.sp,

                fontWeight =
                    FontWeight.Medium
            )
        }
    }
}
