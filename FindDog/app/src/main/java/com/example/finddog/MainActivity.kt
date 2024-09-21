package com.example.finddog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.finddog.DogViewModel.DogViewModel
import com.example.finddog.Navigation.App
import com.example.finddog.ui.theme.FindDogTheme
import com.example.finddog.ui.theme.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(this).get(DogViewModel::class.java)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FindDogTheme {
               // showDogs(viewModel)
               // HomeScreen(viewModel)
                App()
            }
        }
    }
}

@Composable
fun showDogs(dogViewModel: DogViewModel){

    val dogsResult = dogViewModel._dogResult.observeAsState(initial = listOf())

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 200.dp)) {
        Text(text = dogsResult.toString(), modifier = Modifier
            .fillMaxSize()
            .padding(10.dp))

        Text(text = dogsResult.value.toString(), modifier = Modifier
            .fillMaxSize()
            .padding(10.dp))
    }


}


