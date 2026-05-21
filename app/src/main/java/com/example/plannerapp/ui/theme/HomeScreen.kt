package com.example.plannerapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController
) {

    val backgroundColor =
        Color(0xFF050505)

    val cardColor =
        Color(0xFF171717)

    val pinkColor =
        Color(0xFFFF2D55)

    val purpleColor =
        Color(0xFFB026FF)

    val greenColor =
        Color(0xFF00C853)

    val context =
        LocalContext.current

    val manager =
        DataStoreManager(context)

    val scope =
        rememberCoroutineScope()

    var tasks by remember {

        mutableStateOf<List<Task>>(
            emptyList()
        )
    }

    LaunchedEffect(Unit) {

        manager.getTasks.collect {

            tasks = it
        }
    }

    val activeTasks =
        tasks.filter {

            !it.deleted
        }

    val completedTasks =
        activeTasks.count {

            it.completed
        }

    Scaffold(

        containerColor =
            backgroundColor,

        floatingActionButton = {

            FloatingActionButton(

                onClick = {

                    navController.navigate(
                        "add"
                    )
                },

                containerColor =
                    pinkColor,

                shape =
                    RoundedCornerShape(
                        22.dp
                    )

            ) {

                Icon(

                    imageVector =
                        Icons.Default.Add,

                    contentDescription =
                        null,

                    tint =
                        Color.White
                )
            }
        },

        bottomBar = {

            NavigationBar(

                containerColor =
                    Color.Black
            ) {

                NavigationBarItem(

                    selected = true,

                    onClick = { },

                    icon = {

                        Text(
                            "⏰",
                            fontSize = 20.sp
                        )
                    },

                    label = {

                        Text("Home")
                    }
                )

                NavigationBarItem(

                    selected = false,

                    onClick = {

                        navController.navigate(
                            "study"
                        )
                    },

                    icon = {

                        Text(
                            "📚",
                            fontSize = 20.sp
                        )
                    },

                    label = {

                        Text("Study")
                    }
                )

                NavigationBarItem(

                    selected = false,

                    onClick = {

                        navController.navigate(
                            "sleep"
                        )
                    },

                    icon = {

                        Text(
                            "😴",
                            fontSize = 20.sp
                        )
                    },

                    label = {

                        Text("Sleep")
                    }
                )

                NavigationBarItem(

                    selected = false,

                    onClick = {

                        navController.navigate(
                            "reports"
                        )
                    },

                    icon = {

                        Text(
                            "📊",
                            fontSize = 20.sp
                        )
                    },

                    label = {

                        Text("Reports")
                    }
                )

                NavigationBarItem(

                    selected = false,

                    onClick = {

                        navController.navigate(
                            "settings"
                        )
                    },

                    icon = {

                        Icon(

                            imageVector =
                                Icons.Default.Settings,

                            contentDescription =
                                null
                        )
                    },

                    label = {

                        Text("Settings")
                    }
                )
            }
        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(
                    backgroundColor
                )
                .padding(padding)
                .padding(16.dp)

        ) {

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            Text(

                text =
                    "Smart Daily Planner",

                color =
                    Color.White,

                fontSize =
                    34.sp,

                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            if (activeTasks.isNotEmpty()) {

                Card(

                    modifier = Modifier
                        .fillMaxWidth(),

                    shape =
                        RoundedCornerShape(
                            28.dp
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
                            .padding(22.dp),

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Column {

                            Text(

                                text = "Tasks",

                                color = Color.LightGray,

                                fontSize = 16.sp
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(8.dp)
                            )

                            Text(

                                text =
                                    "${activeTasks.size}",

                                color =
                                    Color.White,

                                fontSize =
                                    30.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }

                        Column(

                            horizontalAlignment =
                                Alignment.End
                        ) {

                            Text(

                                text = "Completed",

                                color = Color.LightGray,

                                fontSize = 16.sp
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(8.dp)
                            )

                            Text(

                                text =
                                    "$completedTasks",

                                color =
                                    greenColor,

                                fontSize =
                                    30.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )
            }

            if (activeTasks.isEmpty()) {

                Card(

                    modifier = Modifier
                        .fillMaxWidth(),

                    shape =
                        RoundedCornerShape(
                            30.dp
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
                            .padding(28.dp),

                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        Text(

                            text = "✨",

                            fontSize = 55.sp
                        )

                        Spacer(
                            modifier =
                                Modifier.height(
                                    10.dp
                                )
                        )

                        Text(

                            text =
                                "No Tasks Added",

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
                                    12.dp
                                )
                        )

                        Text(

                            text =
                                "Add your daily goals, study plans, reminders and stay productive every day.",

                            color =
                                Color.LightGray,

                            fontSize =
                                16.sp,

                            lineHeight =
                                24.sp
                        )

                        Spacer(
                            modifier =
                                Modifier.height(
                                    20.dp
                                )
                        )

                        Box(

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                                .background(

                                    brush = Brush.horizontalGradient(

                                        listOf(
                                            pinkColor,
                                            purpleColor
                                        )
                                    ),

                                    shape =
                                        RoundedCornerShape(
                                            50.dp
                                        )
                                )
                        )
                    }
                }

            } else {

                Text(

                    text =
                        "Today's Tasks",

                    color =
                        Color.White,

                    fontSize =
                        24.sp,

                    fontWeight =
                        FontWeight.Bold
                )

                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )

                LazyColumn(

                    verticalArrangement =
                        Arrangement.spacedBy(
                            16.dp
                        ),

                    contentPadding =
                        PaddingValues(
                            bottom = 120.dp
                        )

                ) {

                    items(activeTasks) { task ->

                        var isEditing by remember {

                            mutableStateOf(false)
                        }

                        var editedTitle by remember {

                            mutableStateOf(
                                task.title
                            )
                        }

                        var editedDate by remember {

                            mutableStateOf(
                                task.date
                            )
                        }

                        var editedTime by remember {

                            mutableStateOf(
                                task.time
                            )
                        }

                        Card(

                            modifier = Modifier
                                .fillMaxWidth(),

                            shape =
                                RoundedCornerShape(
                                    30.dp
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
                                    .padding(22.dp)
                            ) {

                                Row(

                                    modifier = Modifier
                                        .fillMaxWidth(),

                                    horizontalArrangement =
                                        Arrangement.SpaceBetween,

                                    verticalAlignment =
                                        Alignment.CenterVertically
                                ) {

                                    Column(

                                        modifier =
                                            Modifier.weight(
                                                1f
                                            )
                                    ) {

                                        Text(

                                            text =
                                                if (isEditing)
                                                    editedDate
                                                else
                                                    task.date,

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
                                                    6.dp
                                                )
                                        )

                                        Text(

                                            text =
                                                if (isEditing)
                                                    editedTime
                                                else
                                                    task.time,

                                            color =
                                                Color.LightGray,

                                            fontSize =
                                                18.sp
                                        )
                                    }

                                    Switch(

                                        checked =
                                            task.completed,

                                        onCheckedChange = { checked ->

                                            val updated =
                                                tasks.map {

                                                    if (
                                                        it.id ==
                                                        task.id
                                                    ) {

                                                        it.copy(
                                                            completed =
                                                                checked
                                                        )

                                                    } else {

                                                        it
                                                    }
                                                }

                                            scope.launch(
                                                Dispatchers.IO
                                            ) {

                                                manager.saveTasks(
                                                    updated
                                                )
                                            }
                                        }
                                    )
                                }

                                Spacer(
                                    modifier =
                                        Modifier.height(
                                            18.dp
                                        )
                                )

                                if (isEditing) {

                                    OutlinedTextField(

                                        value =
                                            editedTitle,

                                        onValueChange = {

                                            editedTitle =
                                                it
                                        },

                                        modifier =
                                            Modifier.fillMaxWidth(),

                                        label = {

                                            Text(
                                                "Edit Task"
                                            )
                                        }
                                    )

                                    Spacer(
                                        modifier =
                                            Modifier.height(
                                                15.dp
                                            )
                                    )

                                    OutlinedTextField(

                                        value =
                                            editedDate,

                                        onValueChange = {

                                            editedDate =
                                                it
                                        },

                                        modifier =
                                            Modifier.fillMaxWidth(),

                                        label = {

                                            Text(
                                                "Edit Date"
                                            )
                                        }
                                    )

                                    Spacer(
                                        modifier =
                                            Modifier.height(
                                                15.dp
                                            )
                                    )

                                    OutlinedTextField(

                                        value =
                                            editedTime,

                                        onValueChange = {

                                            editedTime =
                                                it
                                        },

                                        modifier =
                                            Modifier.fillMaxWidth(),

                                        label = {

                                            Text(
                                                "Edit Time"
                                            )
                                        }
                                    )

                                    Spacer(
                                        modifier =
                                            Modifier.height(
                                                18.dp
                                            )
                                    )

                                    Button(

                                        onClick = {

                                            val updated =
                                                tasks.map {

                                                    if (
                                                        it.id ==
                                                        task.id
                                                    ) {

                                                        it.copy(

                                                            title =
                                                                editedTitle,

                                                            date =
                                                                editedDate,

                                                            time =
                                                                editedTime
                                                        )

                                                    } else {

                                                        it
                                                    }
                                                }

                                            scope.launch(
                                                Dispatchers.IO
                                            ) {

                                                manager.saveTasks(
                                                    updated
                                                )
                                            }

                                            isEditing =
                                                false
                                        },

                                        modifier =
                                            Modifier.fillMaxWidth(),

                                        colors =
                                            ButtonDefaults.buttonColors(
                                                containerColor =
                                                    purpleColor
                                            )

                                    ) {

                                        Text(
                                            "Save Changes"
                                        )
                                    }

                                } else {

                                    Text(

                                        text =
                                            task.title,

                                        color =
                                            Color.White,

                                        fontSize =
                                            24.sp,

                                        fontWeight =
                                            FontWeight.SemiBold,

                                        maxLines = 2,

                                        overflow =
                                            TextOverflow.Ellipsis
                                    )

                                    Spacer(
                                        modifier =
                                            Modifier.height(
                                                12.dp
                                            )
                                    )

                                    Text(

                                        text =
                                            "Priority : ${task.priority}",

                                        color =
                                            pinkColor,

                                        fontSize =
                                            16.sp,

                                        fontWeight =
                                            FontWeight.Medium
                                    )

                                    Spacer(
                                        modifier =
                                            Modifier.height(
                                                22.dp
                                            )
                                    )

                                    Row(

                                        modifier = Modifier
                                            .fillMaxWidth(),

                                        horizontalArrangement =
                                            Arrangement.SpaceBetween
                                    ) {

                                        Button(

                                            onClick = {

                                                isEditing =
                                                    true
                                            },

                                            colors =
                                                ButtonDefaults.buttonColors(
                                                    containerColor =
                                                        purpleColor
                                                )

                                        ) {

                                            Icon(

                                                imageVector =
                                                    Icons.Default.Edit,

                                                contentDescription =
                                                    null
                                            )

                                            Spacer(
                                                modifier =
                                                    Modifier.width(
                                                        6.dp
                                                    )
                                            )

                                            Text(
                                                "Edit"
                                            )
                                        }

                                        Button(

                                            onClick = {

                                                val updated =
                                                    tasks.map {

                                                        if (
                                                            it.id ==
                                                            task.id
                                                        ) {

                                                            it.copy(
                                                                deleted =
                                                                    true
                                                            )

                                                        } else {

                                                            it
                                                        }
                                                    }

                                                scope.launch(
                                                    Dispatchers.IO
                                                ) {

                                                    manager.saveTasks(
                                                        updated
                                                    )
                                                }
                                            },

                                            colors =
                                                ButtonDefaults.buttonColors(
                                                    containerColor =
                                                        Color.Red
                                                )

                                        ) {

                                            Icon(

                                                imageVector =
                                                    Icons.Default.Delete,

                                                contentDescription =
                                                    null,

                                                tint =
                                                    Color.White
                                            )

                                            Spacer(
                                                modifier =
                                                    Modifier.width(
                                                        6.dp
                                                    )
                                            )

                                            Text(

                                                text =
                                                    "Delete",

                                                color =
                                                    Color.White
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
