package com.example.projet4_jimmy_julien.data
import android.content.Context


interface AppContainer {
    val todoRepository: TodoRepository
}


class AppDataContainer(private val context: Context) : AppContainer {

    override val todoRepository: TodoRepository by lazy {
        OfflineTodosRepository(TodoDatabase.getDatabase(context).TodoDao())
    }
}
