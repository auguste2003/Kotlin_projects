package com.example.todo_app_room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel:ViewModel() {
    private var _todoList : MutableLiveData<List<Todo>> = MutableLiveData()
  val todoList : LiveData<List<Todo>> = _todoList

    fun getAllTodo(){
    _todoList.value = TodoManager.getAllTodo().reversed() // changer l'ordre

    }
    fun addTodo(title:String){
     TodoManager.addTodo(title)
      getAllTodo()
    }
    fun deleteTodo(id:Int){
       TodoManager.deleteTodo(id)
      getAllTodo()
    }
}