package com.example.todo_app_room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.todo_app_room.ui.theme.ToDoAppRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val todaViewModel = ViewModelProvider(this)[TodoViewModel::class.java] // initailiser le ViewModel


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoAppRoomTheme {
                TodoListPage(todaViewModel)
            }
        }
    }
}

