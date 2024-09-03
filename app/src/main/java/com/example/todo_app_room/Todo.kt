package com.example.todo_app_room

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date

@Entity
data class Todo(@PrimaryKey(autoGenerate = true)
                var id:Int =0,
                var title:String ,
                var createAt: Date)




fun getFakeTodo(): List<Todo> {
    val now = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Date.from(Instant.now())
    } else {
        Date() // Utilise le constructeur par défaut pour les versions antérieures
    }
    return listOf(
        Todo(1, "First todo", now),
        Todo(2, "Second Todo", now),
        Todo(3, "This is my third Todo", now),
        Todo(4, "This is my forth ToDo", now)
    )
}


