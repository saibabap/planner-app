package com.example.plannerapp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.NoteAlt
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

data class NoteItem(

    val title: String,

    val description: String,

    val date: String
)

@Composable
fun ReportsScreen() {

    val context =
        LocalContext.current

    val backgroundColor =
        Color(0xFF050505)

    val cardColor =
        Color(0xFF171717)

    val pinkColor =
        Color(0xFFFF2D55)

    // ---------------- INPUTS ----------------

    var title by remember {

        mutableStateOf("")
    }

    var description by remember {

        mutableStateOf("")
    }

    // ---------------- DATE ----------------

    fun getCurrentDate(): String {

        return SimpleDateFormat(

            "dd MMM yyyy • hh:mm a",

            Locale.getDefault()

        ).format(Date())
    }

    // ---------------- NOTES ----------------

    var notesList by remember {

        mutableStateOf(

            mutableListOf<NoteItem>()
        )
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

            // ---------------- TITLE ----------------

            Text(

                text = "My Notes",

                color = Color.White,

                fontSize = 34.sp,

                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(25.dp)
            )

            // ---------------- NOTES CARD ----------------

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
                        .padding(22.dp)
                ) {

                    Row(

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(

                            imageVector =
                                Icons.Default.NoteAlt,

                            contentDescription =
                                null,

                            tint = pinkColor,

                            modifier =
                                Modifier.size(35.dp)
                        )

                        Spacer(
                            modifier =
                                Modifier.width(12.dp)
                        )

                        Text(

                            text = "Write Report / Notes",

                            color = Color.White,

                            fontSize = 24.sp,

                            fontWeight =
                                FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(25.dp)
                    )

                    // ---------------- TITLE FIELD ----------------

                    OutlinedTextField(

                        value = title,

                        onValueChange = {

                            title = it
                        },

                        label = {

                            Text("Title")
                        },

                        modifier = Modifier
                            .fillMaxWidth(),

                        colors =
                            OutlinedTextFieldDefaults.colors(

                                focusedBorderColor =
                                    pinkColor,

                                unfocusedBorderColor =
                                    Color.Gray,

                                focusedTextColor =
                                    Color.White,

                                unfocusedTextColor =
                                    Color.White
                            )
                    )

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    // ---------------- DESCRIPTION FIELD ----------------

                    OutlinedTextField(

                        value = description,

                        onValueChange = {

                            description = it
                        },

                        label = {

                            Text("Write your notes...")
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),

                        colors =
                            OutlinedTextFieldDefaults.colors(

                                focusedBorderColor =
                                    pinkColor,

                                unfocusedBorderColor =
                                    Color.Gray,

                                focusedTextColor =
                                    Color.White,

                                unfocusedTextColor =
                                    Color.White
                            )
                    )

                    Spacer(
                        modifier =
                            Modifier.height(25.dp)
                    )

                    // ---------------- SAVE BUTTON ----------------

                    Button(

                        onClick = {

                            if (
                                title.isNotEmpty() &&
                                description.isNotEmpty()
                            ) {

                                notesList =
                                    (
                                            listOf(

                                                NoteItem(

                                                    title,

                                                    description,

                                                    getCurrentDate()
                                                )

                                            ) + notesList
                                            ).toMutableList()

                                title = ""

                                description = ""

                                Toast.makeText(

                                    context,

                                    "Note Saved",

                                    Toast.LENGTH_SHORT

                                ).show()

                            } else {

                                Toast.makeText(

                                    context,

                                    "Fill all fields",

                                    Toast.LENGTH_SHORT

                                ).show()
                            }
                        },

                        modifier = Modifier
                            .fillMaxWidth(),

                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    pinkColor
                            )

                    ) {

                        Text(

                            text = "Save Note",

                            fontSize = 18.sp
                        )
                    }
                }
            }

            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )

            // ---------------- NOTES TITLE ----------------

            Row(

                modifier = Modifier
                    .fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(

                    text = "Saved Notes",

                    color = Color.White,

                    fontSize = 30.sp,

                    fontWeight =
                        FontWeight.Bold
                )

                TextButton(

                    onClick = {

                        notesList =
                            mutableListOf()

                        Toast.makeText(

                            context,

                            "All Notes Deleted",

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
                    Modifier.height(15.dp)
            )
        }

        // ---------------- NOTES LIST ----------------

        itemsIndexed(notesList) { index, note ->

            var isEditing by remember {

                mutableStateOf(false)
            }

            var editedTitle by remember {

                mutableStateOf(note.title)
            }

            var editedDescription by remember {

                mutableStateOf(note.description)
            }

            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp),

                shape =
                    RoundedCornerShape(25.dp),

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

                    if (isEditing) {

                        // ---------------- EDIT TITLE ----------------

                        OutlinedTextField(

                            value = editedTitle,

                            onValueChange = {

                                editedTitle = it
                            },

                            label = {

                                Text("Edit Title")
                            },

                            modifier = Modifier
                                .fillMaxWidth(),

                            colors =
                                OutlinedTextFieldDefaults.colors(

                                    focusedBorderColor =
                                        pinkColor,

                                    unfocusedBorderColor =
                                        Color.Gray,

                                    focusedTextColor =
                                        Color.White,

                                    unfocusedTextColor =
                                        Color.White
                                )
                        )

                        Spacer(
                            modifier =
                                Modifier.height(15.dp)
                        )

                        // ---------------- EDIT DESCRIPTION ----------------

                        OutlinedTextField(

                            value = editedDescription,

                            onValueChange = {

                                editedDescription = it
                            },

                            label = {

                                Text("Edit Note")
                            },

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp),

                            colors =
                                OutlinedTextFieldDefaults.colors(

                                    focusedBorderColor =
                                        pinkColor,

                                    unfocusedBorderColor =
                                        Color.Gray,

                                    focusedTextColor =
                                        Color.White,

                                    unfocusedTextColor =
                                        Color.White
                                )
                        )

                        Spacer(
                            modifier =
                                Modifier.height(20.dp)
                        )

                        // ---------------- SAVE CHANGES ----------------

                        Button(

                            onClick = {

                                notesList[index] =

                                    NoteItem(

                                        editedTitle,

                                        editedDescription,

                                        note.date
                                    )

                                notesList =
                                    notesList.toMutableList()

                                isEditing = false

                                Toast.makeText(

                                    context,

                                    "Note Updated",

                                    Toast.LENGTH_SHORT

                                ).show()
                            },

                            modifier = Modifier
                                .fillMaxWidth(),

                            colors =
                                ButtonDefaults.buttonColors(
                                    containerColor =
                                        pinkColor
                                )

                        ) {

                            Text(
                                "Save Changes"
                            )
                        }

                    } else {

                        // ---------------- NOTE VIEW ----------------

                        Text(

                            text = note.title,

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

                            text = note.description,

                            color = Color.LightGray,

                            fontSize = 16.sp,

                            lineHeight = 24.sp
                        )

                        Spacer(
                            modifier =
                                Modifier.height(15.dp)
                        )

                        Text(

                            text = note.date,

                            color = pinkColor,

                            fontSize = 13.sp
                        )

                        Spacer(
                            modifier =
                                Modifier.height(18.dp)
                        )

                        // ---------------- BUTTONS ----------------

                        Row {

                            Button(

                                onClick = {

                                    isEditing = true
                                },

                                colors =
                                    ButtonDefaults.buttonColors(
                                        containerColor =
                                            pinkColor
                                    )

                            ) {

                                Text(
                                    "Edit"
                                )
                            }

                            Spacer(
                                modifier =
                                    Modifier.width(15.dp)
                            )

                            Button(

                                onClick = {

                                    notesList =
                                        notesList.toMutableList().apply {

                                            removeAt(index)
                                        }

                                    Toast.makeText(

                                        context,

                                        "Note Deleted",

                                        Toast.LENGTH_SHORT

                                    ).show()
                                },

                                colors =
                                    ButtonDefaults.buttonColors(
                                        containerColor =
                                            Color.Red
                                    )

                            ) {

                                Text(
                                    "Delete"
                                )
                            }
                        }
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