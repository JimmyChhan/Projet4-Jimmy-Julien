package com.example.projet4_jimmy_julien

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.projet4_jimmy_julien.ui.theme.Projet4_JimmyJulienTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Projet4_JimmyJulienTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TodoApp()
                }
            }
        }
    }
}
