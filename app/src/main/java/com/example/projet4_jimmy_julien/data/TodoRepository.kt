package com.example.projet4_jimmy_julien.data

import kotlinx.coroutines.flow.Flow

interface TodosRepository {
   
    fun getAllTodosStream(): Flow<List<Todo>>

    fun getTodoStream(id: Int): Flow<Todo?>

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)
}

