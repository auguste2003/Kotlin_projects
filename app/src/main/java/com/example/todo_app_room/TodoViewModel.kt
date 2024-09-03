package com.example.todo_app_room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class TodoViewModel:ViewModel() {

    val todoDao = MainApplication.todoDatabase.getTodoDao()



  val todoList : LiveData<List<Todo>> = todoDao.getAllTodo()


    fun addTodo(title:String){
        // La supréssion se fait dans un threat qui est différent
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTodo(Todo(title = title, createAt =  Date()))
        }
    }

    fun deleteTodo(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(id)
        }

    }
}