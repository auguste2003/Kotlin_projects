package com.example.compose_kurs.componets.Scroffold.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.compose_kurs.componets.Scroffold.screens.data.Student
import kotlinx.serialization.json.Json

@Composable
fun ScreenOne(navController: NavController){
    val student = Student(
        name = "Jane Doe",
        age = 22,
        rollNo = 67890,
        standard = 12
    )
    Surface(modifier = Modifier.fillMaxSize(),

        color = MaterialTheme.colorScheme.background) {

        Column (Modifier.fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center){

            Text(text = "Screen One")

          //  Button(onClick = { navController.navigate("screen-two/Salut screen two ") }) {
            val json = Json{
                prettyPrint = true
            }
            val data = json.encodeToString(Student.serializer(),student)
            Button(onClick = { navController.navigate("screen-two/$data ") }) {
                Text(text = "Click for Screen 2")

            }
        }
    }
}