package com.example.compose_kurs.componets

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_kurs.R
import com.example.compose_kurs.data.CountryInfo
import com.example.compose_kurs.ui.theme.Compose_KursTheme

class ModifierActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModiferScreen("Auguste")
        }
    }
}

@Composable
fun ModiferScreen(name:String) {
  Column(modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center) {
      Text(text = "Hallo $name",
          modifier = Modifier.
          width(200.dp)
              .height(100.dp)
              .clip(RoundedCornerShape(4.dp)) // borderRadius
              .border(1.dp, Color.Black)
              .background(Color.LightGray)
              .clickable { Log.i("Click","Something got clicked") } // mieux le mettre avant le padding()
              .padding(12.dp)


      )
  }
}


@Preview(showBackground = true )
@Composable
fun ModifierPreview(){

    ModiferScreen("Android") ;
}
