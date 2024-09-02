package com.example.compose_kurs.componets.Scroffold.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.compose_kurs.componets.Scroffold.screens.data.Student
import kotlinx.serialization.json.Json

@Composable
fun ScreenTwo(navController: NavController){
    Column (Modifier.fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center){

        Button(onClick = { navController.popBackStack() }) {

            Text(text = "previous")

        }
        val jsonData = navController.currentBackStackEntry?.arguments?.getString("data")?:"Not Data"
        val student = Json { prettyPrint = true }.decodeFromString(Student.serializer(),jsonData)
        Text(text = "Data from Screen one : ${student}")
        Text(text = "Screen two")

        Button(onClick = { navController.navigate("screen-three") }) {

            Text(text = "Click for 3")

        }
    }
}