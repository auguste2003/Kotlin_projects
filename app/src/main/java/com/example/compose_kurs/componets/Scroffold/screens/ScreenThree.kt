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

@Composable
fun ScreenThree(navController: NavController){
    Column (
        Modifier.fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center){

        Button(onClick = { navController.popBackStack() }) {

            Text(text = "previous")

        }
        Text(text = "Screen three")

        Button(onClick = { navController.popBackStack(navController.graph.startDestinationId, inclusive = false) }) { // rentrer au niveau du premier ecran sans poper le premier

            Text(text = "Click for home")

        }
    }
}