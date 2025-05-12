package com.example.projet4_jimmy_julien.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Priority {
    HIGH,
    MEDIUM,
    LOW
}

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dateCreation: String,
    val priority: Priority,
    val nom: String,
    val note: String,
    val done: Boolean,
    val deadLine: String
)
