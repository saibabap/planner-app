package com.example.plannerapp

data class Task(

    val id: Int,

    val title: String,

    val date: String,

    val time: String,

    val priority: String,

    val completed: Boolean = false,

    val deleted: Boolean = false
)