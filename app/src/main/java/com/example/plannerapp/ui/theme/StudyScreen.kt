package com.example.plannerapp

import android.media.MediaPlayer
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
import android.widget.Toast
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

data class StudyTask(

    val title: String,

    var completed: Boolean = false
)

@Composable
fun StudyScreen() {

    val context = LocalContext.current

    val vibrator =
        context.getSystemService(
            Vibrator::class.java
        )

    val backgroundColor =
        Color(0xFF050505)

    val cardColor =
        Color(0xFF171717)

    val greenColor =
        Color(0xFF00E676)

    val orangeColor =
        Color(0xFFFF9800)

    val redColor =
        Color(0xFFFF1744)

    // ---------------- TIMER ----------------

    var customMinutes by remember {

        mutableStateOf("25")
    }

    var timeLeft by remember {

        mutableStateOf(0)
    }

    var running by remember {

        mutableStateOf(false)
    }

    var breakMode by remember {

        mutableStateOf(false)
    }

    var streak by remember {

        mutableStateOf(0)
    }

    // ---------------- TASK ----------------

    var newTask by remember {

        mutableStateOf("")
    }

    var editIndex by remember {

        mutableStateOf(-1)
    }

    var tasks by remember {

        mutableStateOf(

            mutableListOf(

                StudyTask("Android Development"),

                StudyTask("Database Design"),

                StudyTask("Compose UI")
            )
        )
    }

    // ---------------- AUTO UPDATE TIMER ----------------

    LaunchedEffect(customMinutes) {

        if (!running && !breakMode) {

            timeLeft =
                (customMinutes.toIntOrNull()
                    ?: 25) * 60
        }
    }

    // ---------------- TIMER ----------------

    LaunchedEffect(running) {

        while (running && timeLeft > 0) {

            delay(1000)

            timeLeft--
        }

        if (running && timeLeft == 0) {

            running = false

            // SOUND

            try {

                val mediaPlayer =
                    MediaPlayer.create(

                        context,

                        Settings.System.DEFAULT_ALARM_ALERT_URI
                    )

                mediaPlayer.start()

            } catch (_: Exception) {
            }

            // VIBRATION

            vibrator?.vibrate(

                VibrationEffect.createOneShot(

                    1000,

                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )

            Toast.makeText(

                context,

                if (breakMode)
                    "Break Completed"
                else
                    "Study Session Completed!",

                Toast.LENGTH_LONG

            ).show()

            // AUTO BREAK

            if (!breakMode) {

                streak++

                breakMode = true

                timeLeft = 5 * 60

                running = true

            } else {

                breakMode = false

                timeLeft =
                    (customMinutes.toIntOrNull()
                        ?: 25) * 60
            }
        }
    }

    // ---------------- TIME FORMAT ----------------

    val minutes = timeLeft / 60

    val seconds = timeLeft % 60

    val formattedTime = String.format(

        "%02d:%02d",

        minutes,

        seconds
    )

    // ---------------- PROGRESS ----------------

    val completedCount =
        tasks.count { it.completed }

    val progress =
        if (tasks.isNotEmpty())
            completedCount.toFloat() /
                    tasks.size.toFloat()
        else 0f

    // ---------------- ANIMATION ----------------

    val infiniteTransition =
        rememberInfiniteTransition()

    val rotation by infiniteTransition.animateFloat(

        initialValue = 0f,

        targetValue = 360f,

        animationSpec =
            infiniteRepeatable(

                animation = tween(
                    4000
                )
            )
    )

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(18.dp)

    ) {

        item {

            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )

            Text(

                text = "Study Planner",

                color = Color.White,

                fontSize = 32.sp,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(25.dp)
            )

            // ---------------- TIMER CARD ----------------

            Card(

                modifier = Modifier
                    .fillMaxWidth(),

                shape =
                    RoundedCornerShape(30.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor = cardColor
                    )

            ) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(

                        text =
                            if (breakMode)
                                "Break Timer"
                            else
                                "Pomodoro Timer",

                        color = Color.White,

                        fontSize = 28.sp,

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    // INPUT

                    if (!running && !breakMode) {

                        OutlinedTextField(

                            value = customMinutes,

                            onValueChange = {

                                customMinutes = it
                            },

                            label = {

                                Text("Minutes")
                            },

                            modifier =
                                Modifier.fillMaxWidth()
                        )

                        Spacer(
                            modifier =
                                Modifier.height(25.dp)
                        )
                    }

                    // TIMER

                    Text(

                        text = formattedTime,

                        color =
                            if (breakMode)
                                orangeColor
                            else
                                greenColor,

                        fontSize = 60.sp,

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    // CIRCLE

                    CircularProgressIndicator(

                        progress =
                            timeLeft.toFloat() /
                                    ((customMinutes.toIntOrNull()
                                        ?: 25) * 60),

                        modifier = Modifier
                            .size(140.dp)
                            .rotate(rotation),

                        color =
                            if (breakMode)
                                orangeColor
                            else
                                greenColor,

                        strokeWidth = 10.dp
                    )

                    Spacer(
                        modifier =
                            Modifier.height(30.dp)
                    )

                    Row {

                        // START

                        Button(

                            onClick = {

                                if (!running) {

                                    val enteredMinutes =
                                        customMinutes.toIntOrNull()
                                            ?: 25

                                    if (!breakMode) {

                                        if (
                                            timeLeft <= 0
                                        ) {

                                            timeLeft =
                                                enteredMinutes * 60
                                        }
                                    }

                                    running = true
                                }
                            },

                            colors =
                                ButtonDefaults.buttonColors(
                                    containerColor =
                                        greenColor
                                )

                        ) {

                            Text("Start")
                        }

                        Spacer(
                            modifier =
                                Modifier.width(12.dp)
                        )

                        // STOP

                        Button(

                            onClick = {

                                running = false
                            },

                            colors =
                                ButtonDefaults.buttonColors(
                                    containerColor =
                                        redColor
                                )

                        ) {

                            Text("Stop")
                        }

                        Spacer(
                            modifier =
                                Modifier.width(12.dp)
                        )

                        // RESET

                        Button(

                            onClick = {

                                running = false

                                breakMode = false

                                timeLeft =
                                    (customMinutes.toIntOrNull()
                                        ?: 25) * 60
                            }

                        ) {

                            Text("Reset")
                        }
                    }

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    Text(

                        text =
                            "🔥 Streak : $streak Sessions",

                        color = greenColor,

                        fontSize = 22.sp,

                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )

            // ---------------- PROGRESS ----------------

            Text(

                text = "Today's Progress",

                color = Color.White,

                fontSize = 28.sp,

                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )

            LinearProgressIndicator(

                progress = progress,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp),

                color = greenColor
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            Text(

                text =
                    "${(progress * 100).toInt()}% Completed",

                color = Color.LightGray,

                fontSize = 18.sp
            )

            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )

            // ---------------- ADD TASK ----------------

            Text(

                text = "Study Tasks",

                color = Color.White,

                fontSize = 30.sp,

                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(18.dp)
            )

            OutlinedTextField(

                value = newTask,

                onValueChange = {

                    newTask = it
                },

                label = {

                    Text("Add New Task")
                },

                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )

            Button(

                onClick = {

                    if (newTask.isNotEmpty()) {

                        if (editIndex != -1) {

                            tasks[editIndex] =
                                tasks[editIndex].copy(
                                    title = newTask
                                )

                            tasks =
                                tasks.toMutableList()

                            editIndex = -1

                        } else {

                            tasks =
                                (tasks + StudyTask(newTask))
                                    .toMutableList()
                        }

                        newTask = ""
                    }
                }

            ) {

                Icon(

                    imageVector =
                        Icons.Default.Add,

                    contentDescription =
                        null
                )

                Spacer(
                    modifier =
                        Modifier.width(8.dp)
                )

                Text(

                    if (editIndex != -1)
                        "Update Task"
                    else
                        "Add Task"
                )
            }

            Spacer(
                modifier =
                    Modifier.height(25.dp)
            )
        }

        // ---------------- TASK LIST ----------------

        itemsIndexed(tasks) { index, task ->

            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp)
                    .combinedClickable(

                        onClick = {
                        },

                        onLongClick = {

                            tasks =
                                tasks.toMutableList().apply {

                                    removeAt(index)
                                }

                            Toast.makeText(

                                context,

                                "Task Deleted",

                                Toast.LENGTH_SHORT

                            ).show()
                        }
                    ),

                colors =
                    CardDefaults.cardColors(
                        containerColor = cardColor
                    ),

                shape =
                    RoundedCornerShape(25.dp)

            ) {

                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Column {

                        Text(

                            text = task.title,

                            color = Color.White,

                            fontSize = 22.sp
                        )

                        Spacer(
                            modifier =
                                Modifier.height(6.dp)
                        )

                        Text(

                            text = "Study Session",

                            color =
                                Color.LightGray
                        )
                    }

                    Row {

                        // EDIT

                        IconButton(

                            onClick = {

                                newTask =
                                    task.title

                                editIndex =
                                    index
                            }

                        ) {

                            Icon(

                                imageVector =
                                    Icons.Default.Edit,

                                contentDescription =
                                    null,

                                tint = orangeColor
                            )
                        }

                        // CHECKBOX

                        Checkbox(

                            checked =
                                task.completed,

                            onCheckedChange = { checked ->

                                tasks =
                                    tasks.mapIndexed { i, oldTask ->

                                        if (i == index) {

                                            oldTask.copy(
                                                completed = checked
                                            )

                                        } else {

                                            oldTask
                                        }

                                    }.toMutableList()
                            }
                        )
                    }
                }
            }
        }

        item {

            Spacer(
                modifier =
                    Modifier.height(120.dp)
            )
        }
    }
}