package com.example.plannerapp

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("planner")

class DataStoreManager(
    private val context: Context
) {

    companion object {

        val TASKS =
            stringPreferencesKey("tasks")
    }

    suspend fun saveTasks(
        tasks: List<Task>
    ) {

        val data = tasks.joinToString("|") {

            "${it.id},${it.title},${it.date},${it.time},${it.priority},${it.completed},${it.deleted}"
        }

        context.dataStore.edit {

            it[TASKS] = data
        }
    }

    val getTasks: Flow<List<Task>> =
        context.dataStore.data.map {

            val saved =
                it[TASKS] ?: ""

            if (saved.isEmpty())
                return@map emptyList()

            saved.split("|").map { item ->

                val parts = item.split(",")

                Task(

                    id = parts[0].toInt(),

                    title = parts[1],

                    date = parts[2],

                    time = parts[3],

                    priority = parts[4],

                    completed = parts[5].toBoolean(),

                    deleted = parts[6].toBoolean()
                )
            }
        }
}