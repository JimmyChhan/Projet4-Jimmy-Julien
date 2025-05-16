package com.example.projet4_jimmy_julien.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.projet4_jimmy_julien.TodoApplication
import com.example.projet4_jimmy_julien.ui.home.HomeViewModel
import com.example.projet4_jimmy_julien.ui.todo.TodoEditViewModel
import com.example.projet4_jimmy_julien.ui.todo.TodoEntryViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            TodoEditViewModel(
                this.createSavedStateHandle(),
                todoApplication().container.todoRepository
            )
        }
        initializer {
            TodoEntryViewModel(todoApplication().container.todoRepository)
        }

        initializer {
            HomeViewModel(todoApplication().container.todoRepository)
        }
    }
}

fun CreationExtras.todoApplication(): TodoApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TodoApplication)
