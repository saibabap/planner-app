package com.example.plannerapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DeletedTasksScreen() {

    val backgroundColor =
        Color(0xFF0A0A0A)

    val cardColor =
        Color(0xFF1A1A1A)

    val redColor =
        Color(0xFFFF5252)

    val greenColor =
        Color(0xFF00C853)

    val context =
        LocalContext.current

    val manager =
        DataStoreManager(context)

    var deletedTasks by remember {

        mutableStateOf<List<Task>>(
            emptyList()
        )
    }

    // Load Deleted Tasks
    LaunchedEffect(Unit) {

        manager.getTasks.collect {

            deletedTasks =
                it.filter { task ->

                    task.deleted
                }
        }
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(
                backgroundColor
            )
            .padding(20.dp)

    ) {

        // Title
        Text(

            text = "Deleted Tasks",

            color = Color.White,

            fontSize = 30.sp,

            fontWeight =
                FontWeight.Bold
        )

        Spacer(
            modifier =
                Modifier.height(25.dp)
        )

        if (deletedTasks.isEmpty()) {

            Box(

                modifier = Modifier
                    .fillMaxSize(),

                contentAlignment =
                    Alignment.Center
            ) {

                Text(

                    text =
                        "No Deleted Tasks",

                    color =
                        Color.LightGray,

                    fontSize =
                        20.sp
                )
            }

        } else {

            LazyColumn {

                items(deletedTasks) { task ->

                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 8.dp
                            ),

                        colors =
                            CardDefaults.cardColors(
                                containerColor =
                                    cardColor
                            )

                    ) {

                        Column(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)

                        ) {

                            // Task Time
                            Text(

                                text =
                                    task.time,

                                color =
                                    Color.White,

                                fontSize =
                                    28.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(
                                        10.dp
                                    )
                            )

                            // Task Title
                            Text(

                                text =
                                    task.title,

                                color =
                                    Color.LightGray,

                                fontSize =
                                    20.sp
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(
                                        6.dp
                                    )
                            )

                            // Priority
                            Text(

                                text =
                                    "Priority : ${task.priority}",

                                color =
                                    redColor,

                                fontSize =
                                    14.sp
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(
                                        20.dp
                                    )
                            )

                            // Buttons Row
                            Row(

                                modifier =
                                    Modifier.fillMaxWidth(),

                                horizontalArrangement =
                                    Arrangement.SpaceBetween
                            ) {

                                // Restore Button
                                Button(

                                    colors =
                                        ButtonDefaults.buttonColors(
                                            containerColor =
                                                greenColor
                                        ),

                                    onClick = {

                                        val updated =
                                            deletedTasks.map {

                                                if (
                                                    it.id ==
                                                    task.id
                                                ) {

                                                    it.copy(
                                                        deleted =
                                                            false
                                                    )

                                                } else {

                                                    it
                                                }
                                            }

                                        CoroutineScope(
                                            Dispatchers.IO
                                        ).launch {

                                            manager.saveTasks(
                                                updated
                                            )
                                        }
                                    }

                                ) {

                                    Icon(

                                        imageVector =
                                            Icons.Default.Restore,

                                        contentDescription =
                                            null
                                    )

                                    Spacer(
                                        modifier =
                                            Modifier.width(
                                                5.dp
                                            )
                                    )

                                    Text("Restore")
                                }

                                // Delete Forever
                                Button(

                                    colors =
                                        ButtonDefaults.buttonColors(
                                            containerColor =
                                                redColor
                                        ),

                                    onClick = {

                                        val updated =
                                            deletedTasks.filter {

                                                it.id !=
                                                        task.id
                                            }

                                        CoroutineScope(
                                            Dispatchers.IO
                                        ).launch {

                                            manager.saveTasks(
                                                updated
                                            )
                                        }
                                    }

                                ) {

                                    Icon(

                                        imageVector =
                                            Icons.Default.Delete,

                                        contentDescription =
                                            null
                                    )

                                    Spacer(
                                        modifier =
                                            Modifier.width(
                                                5.dp
                                            )
                                    )

                                    Text("Delete Forever")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}