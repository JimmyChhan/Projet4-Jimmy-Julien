package com.example.projet4_jimmy_julien.ui.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
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
            //changer pour les attributs des todos
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}


data class TodoUiState(
    val todoDetails: TodoDetails = TodoDetails(),
    val isEntryValid: Boolean = false
)

data class TodoDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)


fun TodoDetails.toTodo(): Todo = Todo(
    id = id,
    dateCreation = TODO(),
    priority = TODO(),
    nom = TODO(),
    note = TODO(),
    done = TODO(),
    deadLine = TODO()

)

//fun date


fun Todo.toTodoUiState(isEntryValid: Boolean = false): TodoUiState = TodoUiState(
    todoDetails = this.toTodoDetails(),
    isEntryValid = isEntryValid
)


fun Todo.toTodoDetails(): TodoDetails = TodoDetails(
    id = id
    //ajouter les attributs du todo
)
