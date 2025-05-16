package com.example.projet4_jimmy_julien.ui.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.projet4_jimmy_julien.data.Priority
import com.example.projet4_jimmy_julien.data.Todo
import com.example.projet4_jimmy_julien.data.TodoRepository


class TodoEntryViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    var todoUiState by mutableStateOf(TodoUiState())
        private set


    fun updateUiState(todoDetails: TodoDetails) {
        todoUiState =
            TodoUiState(todoDetails = todoDetails, isEntryValid = validateInput(todoDetails))
    }

    suspend fun saveTodo() {
        if (validateInput()) {
            todoRepository.insertTodo(todoUiState.todoDetails.toTodo())
        }
    }

    private fun validateInput(uiState: TodoDetails = todoUiState.todoDetails): Boolean {
        return with(uiState) {

            nom.isNotBlank()
                    && note.isNotBlank()
                    && deadLine.isNotBlank()
        }
    }
}


data class TodoUiState(
    val todoDetails: TodoDetails = TodoDetails(),
    val isEntryValid: Boolean = false
)

data class TodoDetails(
    val id: Int = 0,
    val dateCreation: String = "",
    val priority: Priority = Priority.LOW,
    val nom: String = "",
    val note: String ="",
    val done: Boolean = false,
    val deadLine:String = ""
)


fun TodoDetails.toTodo(): Todo = Todo(
    id = id,
    dateCreation = dateCreation,
    priority = priority,
    nom = nom,
    note = note,
    done = done,
    deadLine = deadLine

)

//fun date


fun Todo.toTodoUiState(isEntryValid: Boolean = false): TodoUiState = TodoUiState(
    todoDetails = this.toTodoDetails(),
    isEntryValid = isEntryValid
)


fun Todo.toTodoDetails(): TodoDetails = TodoDetails(
    id = id,
    dateCreation = dateCreation,
    priority = priority,
    nom = nom,
    note = note,
    done = done,
    deadLine = deadLine

)
