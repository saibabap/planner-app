package com.example.plannerapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Calendar

@Composable
fun AddTaskScreen(
    navController: NavController
) {

    val backgroundColor =
        Color(0xFF050505)

    val cardColor =
        Color(0xFF171717)

    val pinkColor =
        Color(0xFFFF2D55)

    val highColor =
        Color(0xFFFF5252)

    val mediumColor =
        Color(0xFFFFC107)

    val lowColor =
        Color(0xFF00C853)

    val context =
        LocalContext.current

    val manager =
        DataStoreManager(context)

    val scope =
        rememberCoroutineScope()

    var task by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var time by remember {
        mutableStateOf("")
    }

    var date by remember {
        mutableStateOf("")
    }

    var priority by remember {
        mutableStateOf("Medium")
    }

    var reminder5Min by remember {
        mutableStateOf(true)
    }

    val priorities = listOf(
        "High",
        "Medium",
        "Low"
    )

    val calendar =
        Calendar.getInstance()

    val timePicker =
        TimePickerDialog(

            context,

            { _, hour, minute ->

                val amPm =
                    if (hour >= 12)
                        "PM"
                    else
                        "AM"

                val formattedHour =
                    when {

                        hour == 0 -> 12

                        hour > 12 -> hour - 12

                        else -> hour
                    }

                val formattedMinute =
                    minute.toString()
                        .padStart(2, '0')

                time =
                    "$formattedHour:$formattedMinute $amPm"
            },

            calendar.get(
                Calendar.HOUR_OF_DAY
            ),

            calendar.get(
                Calendar.MINUTE
            ),

            false
        )

    val datePicker =
        DatePickerDialog(

            context,

            { _, year, month, day ->

                date =
                    "$day/${month + 1}/$year"
            },

            calendar.get(
                Calendar.YEAR
            ),

            calendar.get(
                Calendar.MONTH
            ),

            calendar.get(
                Calendar.DAY_OF_MONTH
            )
        )

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(
                backgroundColor
            )
            .verticalScroll(
                rememberScrollState()
            )
            .padding(20.dp)

    ) {

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        Text(

            text = "Create Task",

            color = Color.White,

            fontSize = 34.sp,

            fontWeight =
                FontWeight.Bold
        )

        Spacer(
            modifier =
                Modifier.height(30.dp)
        )

        Text(

            text = "Task Name",

            color = Color.White,

            fontSize = 18.sp,

            fontWeight =
                FontWeight.Medium
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        OutlinedTextField(

            value = task,

            onValueChange = {

                task = it
            },

            placeholder = {

                Text(
                    "Enter task title"
                )
            },

            modifier = Modifier
                .fillMaxWidth(),

            shape =
                RoundedCornerShape(
                    20.dp
                ),

            colors =
                OutlinedTextFieldDefaults.colors(

                    focusedBorderColor =
                        pinkColor,

                    unfocusedBorderColor =
                        Color.DarkGray,

                    focusedTextColor =
                        Color.White,

                    unfocusedTextColor =
                        Color.White,

                    focusedContainerColor =
                        cardColor,

                    unfocusedContainerColor =
                        cardColor
                )
        )

        Spacer(
            modifier =
                Modifier.height(25.dp)
        )

        Text(

            text = "Description",

            color = Color.White,

            fontSize = 18.sp,

            fontWeight =
                FontWeight.Medium
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        OutlinedTextField(

            value = description,

            onValueChange = {

                description = it
            },

            placeholder = {

                Text(
                    "Task description"
                )
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),

            shape =
                RoundedCornerShape(
                    20.dp
                ),

            colors =
                OutlinedTextFieldDefaults.colors(

                    focusedBorderColor =
                        pinkColor,

                    unfocusedBorderColor =
                        Color.DarkGray,

                    focusedTextColor =
                        Color.White,

                    unfocusedTextColor =
                        Color.White,

                    focusedContainerColor =
                        cardColor,

                    unfocusedContainerColor =
                        cardColor
                )
        )

        Spacer(
            modifier =
                Modifier.height(25.dp)
        )

        Text(

            text = "Select Date",

            color = Color.White,

            fontSize = 18.sp,

            fontWeight =
                FontWeight.Medium
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        Button(

            onClick = {

                datePicker.show()
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),

            shape =
                RoundedCornerShape(
                    20.dp
                ),

            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        cardColor
                )

        ) {

            Row(

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Icon(

                    imageVector =
                        Icons.Default.DateRange,

                    contentDescription =
                        null,

                    tint =
                        pinkColor
                )

                Spacer(
                    modifier =
                        Modifier.width(
                            12.dp
                        )
                )

                Text(

                    text =
                        if (date.isEmpty())
                            "Choose Date"
                        else
                            date,

                    color =
                        Color.White,

                    fontSize =
                        18.sp
                )
            }
        }

        Spacer(
            modifier =
                Modifier.height(20.dp)
        )

        Text(

            text = "Select Time",

            color = Color.White,

            fontSize = 18.sp,

            fontWeight =
                FontWeight.Medium
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        Button(

            onClick = {

                timePicker.show()
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),

            shape =
                RoundedCornerShape(
                    20.dp
                ),

            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        cardColor
                )

        ) {

            Row(

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Icon(

                    imageVector =
                        Icons.Default.AccessTime,

                    contentDescription =
                        null,

                    tint =
                        pinkColor
                )

                Spacer(
                    modifier =
                        Modifier.width(
                            12.dp
                        )
                )

                Text(

                    text =
                        if (time.isEmpty())
                            "Choose Time"
                        else
                            time,

                    color =
                        Color.White,

                    fontSize =
                        18.sp
                )
            }
        }

        Spacer(
            modifier =
                Modifier.height(25.dp)
        )

        Text(

            text = "Priority",

            color = Color.White,

            fontSize = 20.sp,

            fontWeight =
                FontWeight.Bold
        )

        Spacer(
            modifier =
                Modifier.height(15.dp)
        )

        priorities.forEach { item ->

            val priorityColor =
                when (item) {

                    "High" ->
                        highColor

                    "Medium" ->
                        mediumColor

                    else ->
                        lowColor
                }

            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 8.dp
                    ),

                shape =
                    RoundedCornerShape(
                        20.dp
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
                        .padding(16.dp),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    RadioButton(

                        selected =
                            priority == item,

                        onClick = {

                            priority = item
                        },

                        colors =
                            RadioButtonDefaults.colors(
                                selectedColor =
                                    priorityColor
                            )
                    )

                    Spacer(
                        modifier =
                            Modifier.width(
                                10.dp
                            )
                    )

                    Text(

                        text = item,

                        color =
                            priorityColor,

                        fontSize = 18.sp,

                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }
        }

        Spacer(
            modifier =
                Modifier.height(25.dp)
        )

        Card(

            shape =
                RoundedCornerShape(
                    20.dp
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

                Column {

                    Text(

                        text =
                            "5 Min Reminder",

                        color =
                            Color.White,

                        fontSize =
                            18.sp,

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(
                                4.dp
                            )
                    )

                    Text(

                        text =
                            "Notify before task",

                        color =
                            Color.LightGray,

                        fontSize =
                            14.sp
                    )
                }

                Switch(

                    checked =
                        reminder5Min,

                    onCheckedChange = {

                        reminder5Min =
                            it
                    }
                )
            }
        }

        Spacer(
            modifier =
                Modifier.height(40.dp)
        )

        Button(

            onClick = {

                if (
                    task.isEmpty() ||
                    date.isEmpty() ||
                    time.isEmpty()
                ) {

                    Toast.makeText(

                        context,

                        "Please fill all fields",

                        Toast.LENGTH_SHORT

                    ).show()

                } else {

                    val newTask =
                        Task(

                            id =
                                System.currentTimeMillis()
                                    .toInt(),

                            title =
                                task,

                            date =
                                date,

                            time =
                                time,

                            priority =
                                priority,

                            completed =
                                false,

                            deleted =
                                false
                        )

                    scope.launch(
                        Dispatchers.IO
                    ) {

                        val oldTasks =
                            manager
                                .getTasks
                                .first()

                        manager.saveTasks(
                            oldTasks + newTask
                        )
                    }

                    NotificationHelper(
                        context
                    ).showNotification(
                        task
                    )

                    AlarmScheduler(
                        context
                    ).scheduleAlarm(

                        System.currentTimeMillis()
                                + 60000
                    )

                    Toast.makeText(

                        context,

                        "Task Added Successfully",

                        Toast.LENGTH_SHORT

                    ).show()

                    task = ""
                    description = ""
                    date = ""
                    time = ""
                    priority = "Medium"

                    navController.popBackStack()
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),

            shape =
                RoundedCornerShape(
                    24.dp
                ),

            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        pinkColor
                )

        ) {

            Text(

                text = "Save Task",

                color = Color.White,

                fontSize = 22.sp,

                fontWeight =
                    FontWeight.Bold
            )
        }

        Spacer(
            modifier =
                Modifier.height(40.dp)
        )
    }
}
