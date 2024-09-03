package com.example.todo_app_room

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TodoListPage( viewModel: TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState()// Liste de todos factices
    var inputText by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        
        Row(
          modifier = Modifier
              .fillMaxWidth()
              .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(value =inputText , onValueChange ={
                inputText = it
            } )
            Button(onClick = {viewModel.addTodo(inputText)
                               inputText = ""       }) {

                Text(text = "ADD")

            }
        }
        todoList?.let { LazyColumn {
            itemsIndexed(todoList!!) { index: Int, item: Todo ->
                // Ici tu vas afficher chaque Todo
                ToDoItem(item, onDelete = {
                    viewModel.deleteTodo(item.id)
                })
            }
        } }?: Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            text = "No Item yet ")

    }
}





@Composable
fun ToDoItem(item:Todo,onDelete : () -> Unit){
  //  Text(text = item.toString())
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.primary)
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically){
        Column(modifier = Modifier.weight(1f)) {// Permetre de pousser l'icon vers le droit du Row 
            Text(text = SimpleDateFormat("HH:mm:aa,dd/mm", Locale.ENGLISH).format(item.createAt),
                fontSize = 12.sp,
               color =  Color.White
            )
            Text(text = item.title,
                fontSize = 12.sp,
              color =   Color.White)
        }
        IconButton(onClick = { onDelete() }) {
           Icon(painter = painterResource(id = R.drawable.ic_delete_todo),
               contentDescription = "Delete",
               tint = Color.White)
        }
    }
}

