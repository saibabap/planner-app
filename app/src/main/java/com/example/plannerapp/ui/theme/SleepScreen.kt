package com.example.plannerapp

import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

data class SleepRecord(

    val day: String,

    val date: String,

    val sleepTime: String,

    val wakeTime: String,

    val totalHours: String,

    val status: String
)

@Composable
fun SleepScreen() {

    val context = LocalContext.current

    val backgroundColor =
        Color(0xFF050505)

    val cardColor =
        Color(0xFF171717)

    val purpleColor =
        Color(0xFFB026FF)

    // ---------------- SWITCH ----------------

    var reminderEnabled by remember {

        mutableStateOf(false)
    }

    // ---------------- TIME ----------------

    var sleepTime by remember {

        mutableStateOf("10:30 PM")
    }

    var wakeTime by remember {

        mutableStateOf("06:00 AM")
    }

    // ---------------- TODAY ----------------

    val todayDate =
        SimpleDateFormat(
            "dd MMM yyyy",
            Locale.getDefault()
        ).format(Date())

    val todayDay =
        SimpleDateFormat(
            "EEEE",
            Locale.getDefault()
        ).format(Date())

    // ---------------- HISTORY ----------------

    var sleepHistory by remember {

        mutableStateOf(

            mutableListOf(

                SleepRecord(

                    "Monday",

                    "19 May 2026",

                    "11:00 PM",

                    "06:00 AM",

                    "7h 0m",

                    "Completed"
                ),

                SleepRecord(

                    "Tuesday",

                    "20 May 2026",

                    "10:30 PM",

                    "05:30 AM",

                    "7h 0m",

                    "Completed"
                )
            )
        )
    }

    // ---------------- TIME PICKERS ----------------

    fun openSleepPicker() {

        val calendar =
            Calendar.getInstance()

        TimePickerDialog(

            context,

            { _, hour, minute ->

                val amPm =
                    if (hour >= 12)
                        "PM"
                    else
                        "AM"

                val formattedHour =
                    if (hour > 12)
                        hour - 12
                    else if (hour == 0)
                        12
                    else hour

                sleepTime =
                    String.format(

                        "%02d:%02d %s",

                        formattedHour,

                        minute,

                        amPm
                    )
            },

            calendar.get(Calendar.HOUR_OF_DAY),

            calendar.get(Calendar.MINUTE),

            false

        ).show()
    }

    fun openWakePicker() {

        val calendar =
            Calendar.getInstance()

        TimePickerDialog(

            context,

            { _, hour, minute ->

                val amPm =
                    if (hour >= 12)
                        "PM"
                    else
                        "AM"

                val formattedHour =
                    if (hour > 12)
                        hour - 12
                    else if (hour == 0)
                        12
                    else hour

                wakeTime =
                    String.format(

                        "%02d:%02d %s",

                        formattedHour,

                        minute,

                        amPm
                    )
            },

            calendar.get(Calendar.HOUR_OF_DAY),

            calendar.get(Calendar.MINUTE),

            false

        ).show()
    }

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(18.dp)

    ) {

        item {

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            Text(

                text = "Sleep Tracker",

                color = Color.White,

                fontSize = 34.sp,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(25.dp)
            )

            // ---------------- TODAY CARD ----------------

            Card(

                modifier = Modifier
                    .fillMaxWidth(),

                shape =
                    RoundedCornerShape(28.dp),

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

                    Icon(

                        imageVector =
                            Icons.Default.NightsStay,

                        contentDescription =
                            null,

                        tint = purpleColor,

                        modifier =
                            Modifier.size(70.dp)
                    )

                    Spacer(
                        modifier =
                            Modifier.height(15.dp)
                    )

                    Text(

                        text = "Today's Sleep",

                        color = Color.White,

                        fontSize = 22.sp,

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(10.dp)
                    )

                    Text(

                        text = "Sleep Better 😴",

                        color = purpleColor,

                        fontSize = 38.sp,

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(18.dp)
                    )

                    // ---------------- INFO TEXT ----------------

                    Text(

                        text =
                            "Adults aged 15 to 65 years should sleep around 7 to 9 hours daily for better brain health, focus, memory and energy.",

                        color = Color.LightGray,

                        fontSize = 15.sp,

                        lineHeight = 24.sp
                    )

                    Spacer(
                        modifier =
                            Modifier.height(15.dp)
                    )

                    Text(

                        text =
                            "Good bedtime habits improve mood, reduce stress and help your body recover faster.",

                        color = Color(0xFFD8B4FE),

                        fontSize = 14.sp,

                        lineHeight = 22.sp
                    )

                    Spacer(
                        modifier =
                            Modifier.height(15.dp)
                    )

                    Text(

                        text =
                            "Best bedtime: 10:00 PM to 11:00 PM for healthy sleep cycle.",

                        color = Color(0xFFB8B8FF),

                        fontSize = 14.sp,

                        lineHeight = 22.sp,

                        fontWeight =
                            FontWeight.Medium
                    )

                    Spacer(
                        modifier =
                            Modifier.height(18.dp)
                    )

                    Text(

                        text =
                            "$todayDay • $todayDate",

                        color = Color.LightGray,

                        fontSize = 18.sp
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(25.dp)
            )

            // ---------------- BEDTIME CARD ----------------

            Card(

                modifier = Modifier
                    .fillMaxWidth(),

                shape =
                    RoundedCornerShape(25.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor = cardColor
                    )

            ) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {

                    Row(

                        modifier = Modifier
                            .fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween,

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Row(

                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            Icon(

                                imageVector =
                                    Icons.Default.Bedtime,

                                contentDescription =
                                    null,

                                tint = purpleColor
                            )

                            Spacer(
                                modifier =
                                    Modifier.width(12.dp)
                            )

                            Column {

                                Text(

                                    text =
                                        "Bedtime Reminder",

                                    color =
                                        Color.White,

                                    fontSize =
                                        22.sp,

                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Spacer(
                                    modifier =
                                        Modifier.height(5.dp)
                                )

                                Text(

                                    text =
                                        if (reminderEnabled)
                                            "Reminder ON"
                                        else
                                            "Reminder OFF",

                                    color =
                                        Color.LightGray
                                )
                            }
                        }

                        Switch(

                            checked =
                                reminderEnabled,

                            onCheckedChange = {

                                reminderEnabled =
                                    it

                                Toast.makeText(

                                    context,

                                    if (it)
                                        "Reminder Enabled"
                                    else
                                        "Reminder Disabled",

                                    Toast.LENGTH_SHORT

                                ).show()
                            }
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    // ---------------- TIME BUTTONS ----------------

                    Row(

                        modifier = Modifier
                            .fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Button(

                            onClick = {

                                openSleepPicker()
                            },

                            colors =
                                ButtonDefaults.buttonColors(
                                    containerColor =
                                        purpleColor
                                )

                        ) {

                            Text(
                                "Sleep : $sleepTime"
                            )
                        }

                        Button(

                            onClick = {

                                openWakePicker()
                            },

                            colors =
                                ButtonDefaults.buttonColors(
                                    containerColor =
                                        purpleColor
                                )

                        ) {

                            Text(
                                "Wake : $wakeTime"
                            )
                        }
                    }

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    // ---------------- SAVE BUTTON ----------------

                    Button(

                        onClick = {

                            try {

                                val format =
                                    SimpleDateFormat(
                                        "hh:mm a",
                                        Locale.getDefault()
                                    )

                                val sleepDate =
                                    format.parse(sleepTime)

                                val wakeDate =
                                    format.parse(wakeTime)

                                var diff =
                                    wakeDate.time - sleepDate.time

                                if (diff < 0) {

                                    diff +=
                                        24 * 60 * 60 * 1000
                                }

                                val hours =
                                    diff /
                                            (1000 * 60 * 60)

                                val minutes =
                                    (diff /
                                            (1000 * 60)) % 60

                                val totalSleep =
                                    "${hours}h ${minutes}m"

                                val status =
                                    if (hours >= 6)
                                        "Completed"
                                    else
                                        "Ongoing"

                                val newRecord =
                                    SleepRecord(

                                        todayDay,

                                        todayDate,

                                        sleepTime,

                                        wakeTime,

                                        totalHours =
                                            totalSleep,

                                        status =
                                            status
                                    )

                                sleepHistory =
                                    (listOf(newRecord)
                                            + sleepHistory)
                                        .toMutableList()

                                Toast.makeText(

                                    context,

                                    "Sleep Saved",

                                    Toast.LENGTH_SHORT

                                ).show()

                            } catch (e: Exception) {

                                Toast.makeText(

                                    context,

                                    "Invalid Time",

                                    Toast.LENGTH_SHORT

                                ).show()
                            }
                        },

                        modifier = Modifier
                            .fillMaxWidth(),

                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    purpleColor
                            )

                    ) {

                        Text(
                            "Save Sleep Record"
                        )
                    }
                }
            }

            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )

            // ---------------- HISTORY TITLE ----------------

            Row(

                modifier = Modifier
                    .fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(

                    text = "Sleep History",

                    color = Color.White,

                    fontSize = 30.sp,

                    fontWeight = FontWeight.Bold
                )

                TextButton(

                    onClick = {

                        sleepHistory =
                            mutableListOf()

                        Toast.makeText(

                            context,

                            "History Cleared",

                            Toast.LENGTH_SHORT

                        ).show()
                    }

                ) {

                    Icon(

                        imageVector =
                            Icons.Default.Delete,

                        contentDescription =
                            null,

                        tint = Color.Red
                    )

                    Spacer(
                        modifier =
                            Modifier.width(5.dp)
                    )

                    Text(

                        text = "Clear",

                        color = Color.Red
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )
        }

        // ---------------- HISTORY ----------------

        items(sleepHistory) { sleep ->

            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp),

                shape =
                    RoundedCornerShape(25.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor = cardColor
                    )

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

                            text =
                                "${sleep.day} • ${sleep.date}",

                            color = Color.White,

                            fontSize = 20.sp,

                            fontWeight =
                                FontWeight.Bold
                        )

                        Spacer(
                            modifier =
                                Modifier.height(10.dp)
                        )

                        Text(

                            text =
                                "Sleep : ${sleep.sleepTime}",

                            color = Color.LightGray,

                            fontSize = 18.sp
                        )

                        Spacer(
                            modifier =
                                Modifier.height(5.dp)
                        )

                        Text(

                            text =
                                "Wake : ${sleep.wakeTime}",

                            color = Color.LightGray,

                            fontSize = 18.sp
                        )
                    }

                    Column(

                        horizontalAlignment =
                            Alignment.End
                    ) {

                        Text(

                            text =
                                sleep.totalHours,

                            color = purpleColor,

                            fontSize = 24.sp,

                            fontWeight =
                                FontWeight.Bold
                        )

                        Spacer(
                            modifier =
                                Modifier.height(6.dp)
                        )

                        Text(

                            text =
                                sleep.status,

                            color =
                                if (sleep.status == "Completed")
                                    Color.Green
                                else
                                    Color.Yellow,

                            fontSize = 14.sp,

                            fontWeight =
                                FontWeight.Bold
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