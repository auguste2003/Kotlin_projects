package com.example.compose_kurs

import App
import com.example.compose_kurs.componets.Scroffold.screens.ScreenOne

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.compose_kurs.componets.Scroffold.screens.ScreenThree
import com.example.compose_kurs.componets.Scroffold.screens.ScreenTwo


class NavigatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background) {
                App()
            }
        }
    }
}



@Preview(showBackground = true )
@Composable
fun ScreenOnePreview(){
    val navController = rememberNavController()
ScreenOne(navController )

}
@Preview(showBackground = true )
@Composable
fun ScreenTwoPreview(){
    val navController = rememberNavController()
    ScreenTwo(navController )

}
@Preview(showBackground = true )
@Composable
fun ScreenThreePreview(){
    val navController = rememberNavController()
    ScreenThree(navController )

}
