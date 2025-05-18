package com.example.projet4_jimmy_julien.ui.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet4_jimmy_julien.data.TodoRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TodoEditViewModel(
    savedStateHandle: SavedStateHandle,

    private val todoRepository: TodoRepository

) : ViewModel() {


    var todoUiState by mutableStateOf(TodoUiState())
        private set

    private val todoId: Int = checkNotNull(savedStateHandle[TodoEditDestination.todoIdArg])

    private fun validateInput(uiState: TodoDetails = todoUiState.todoDetails): Boolean {
        return with(uiState) {
            nom.isNotBlank()
                    && note.isNotBlank()

        }
    }

    init {
        viewModelScope.launch {
            todoUiState = todoRepository.getTodoStream(todoId)
                .filterNotNull()
                .first()
                .toTodoUiState(true)
        }

    }

    fun updateUiState(todoDetails: TodoDetails) {
        todoUiState =
            TodoUiState(todoDetails = todoDetails, isEntryValid = validateInput(todoDetails))
    }

    suspend fun updateTodo() {
        if (validateInput(todoUiState.todoDetails)) {
            todoRepository.updateTodo(todoUiState.todoDetails.toTodo())
        }
    }




}
