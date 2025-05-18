package com.example.projet4_jimmy_julien.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet4_jimmy_julien.data.Todo
import com.example.projet4_jimmy_julien.data.TodoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val todoRepository: TodoRepository,
) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        todoRepository.getAllTodosStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
        }
    }

    fun checkTodo(todo: Todo) {
        val newTodo = todo.copy(done = !todo.done)
        viewModelScope.launch {
            todoRepository.updateTodo(newTodo)
        }
    }



    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }


}



data class HomeUiState(val todoList: List<Todo> = listOf())