package com.example.projet4_jimmy_julien

import android.app.Application
import com.example.projet4_jimmy_julien.data.AppContainer
import com.example.projet4_jimmy_julien.data.AppDataContainer

class TodoApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
