package com.example.inventory.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.projet4_jimmy_julien.TodoApplication
import com.example.projet4_jimmy_julien.ui.viewmodels.HomeViewModel
import com.example.projet4_jimmy_julien.ui.viewmodels.todoEditViewModel
import com.example.projet4_jimmy_julien.ui.viewmodels.TodoEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            TodoEditViewModel(
                this.createSavedStateHandle(),
                TodoApplication().container.todoRepository
            )
        }
        // Initializer for ItemEntryViewModel
        initializer {
            TodoEntryViewModel(TodoApplication().container.todoRepository)
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(TodoApplication().container.todoRepository)
        }
    }
}

fun CreationExtras.todoApplication(): TodoApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TodoApplication)
