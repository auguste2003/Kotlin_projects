package com.example.compose_kurs

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Button
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    private val mainActivityViewModel by viewModels<MainActivityViewModel>() // Le changement d'orientation de l'écran ne change pas les données
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen(mainActivityViewModel)
        }
    }
}

@Composable
fun MainScreen(mainActivityViewModel: MainActivityViewModel) {
   // var counter = 0 // State of the counter
  var counter by rememberSaveable { mutableStateOf(1)} // rememberSaveable  la valeur ne change pas apres orientation
   /* var increaseCounter ={
        Log.i( "MainScreen: ","Counter value ist ${counter}")
        counter
    }
    var decrementCounter = {
        Log.i( "MainScreen: ","Counter value ist ${counter}")
        counter
    }

   */
  //  mainActivityViewModel.counter?.observersAsState()?.value.get{}
   /* mainActivityViewModel.error.observeAsState().?value?.let{
        Toast.makeText((LocalContext.current,it.toString(),Toast.LENGTH_SHOT).show())
    }

    */
  //  val counter by mainActivityViewModel.counter.observeAsState(0)
    val counterBackgroundColor by remember {
        derivedStateOf {
            if(counter % 2 == 0){
                Color.LightGray
            }else {
                Color.Black
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), // Fügt etwas Abstand zum Rand hinzu
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.End
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), // Fügt Abstand zwischen den Buttons hinzu
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                   // increaseCounter.invoke()
                    mainActivityViewModel.increaseCounter?.invoke()
                    Log.i("MainScreen", "Button 1 clicked")
                          },
                modifier = Modifier.padding(10.dp)

            ) {
                Text("increase")
            }
            Text(text = "${ mainActivityViewModel.counter?.value}",
                modifier = Modifier.background(counterBackgroundColor))
            Button(
                onClick = {
                    mainActivityViewModel.decrementCounter?.invoke()
                    Log.i("MainScreen", "Button 2 clicked")
                          },
                modifier = Modifier.padding(10.dp)
            ) {
                Text("decrease")
            }
        }
    }
}
@Preview(showBackground = true )
@Composable
fun MainScrennPreview(){
    MainScreen(MainActivityViewModel()) ;
}
