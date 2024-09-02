package com.example.compose_kurs
import android.os.Bundle
import android.view.Surface

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_kurs.componets.CountryCard
import com.example.compose_kurs.componets.CountryCardCoil
import com.example.compose_kurs.componets.CountryCardContraintLayoutBarrier
import com.example.compose_kurs.componets.CountryCardContraintLayoutChains

import com.example.compose_kurs.componets.CountryCardContraintLayoutGuidline
import com.example.compose_kurs.componets.CountryCardWidthConstraintLayout
import com.example.compose_kurs.data.CountryInfo
import com.example.compose_kurs.ui.theme.Compose_KursTheme


class FlagActivity : ComponentActivity() {
    private val augusteInfo = CountryInfo(R.drawable.scheinbild,"Auguste","Gie?en","Hessen","Afrique centrale","FCA","+237","in")
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlagScreen(augusteInfo)
        }
    }
}

@Composable
fun FlagScreen(augusteInfo:CountryInfo) {

    Surface(modifier = Modifier
        .fillMaxSize()
        .wrapContentHeight(align = Alignment.Top)
        .padding(5.dp),
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(25)



   ) { // Couleur blanche pour le font de la suface
        CountryCardContraintLayoutBarrier(augusteInfo)
     //   CountryCard(augusteInfo)
       // CountryCardWidthConstraintLayout(augusteInfo) // Avec contraintLayout
      //  CountryCardContraintLayoutGuidline(augusteInfo) // With guideLine
      // CountryCardContraintLayoutBarrier(augusteInfo)
      //  CountryCardContraintLayoutChains(augusteInfo)
 /*LazyColumn {  // Une colone scrollable .
    item {
        CountryCardContraintLayoutBarrier(augusteInfo)
        CountryCardContraintLayoutBarrier(augusteInfo)
        CountryCardContraintLayoutBarrier(augusteInfo)
        CountryCardContraintLayoutBarrier(augusteInfo)
        CountryCardContraintLayoutBarrier(augusteInfo)
        CountryCardContraintLayoutBarrier(augusteInfo)
        CountryCardContraintLayoutBarrier(augusteInfo)
    }
}
*/



     }
}


@Preview(showBackground = true )
@Composable
fun FlagScreenPreview(){
     val augusteInfo = CountryInfo(R.drawable.scheinbild,"Auguste","Gie?en","Hessen","Afrique centrale","FCA","+237","in")

  //  FlagScreen(augusteInfo) ;
   //  CountryCardCoil(augusteInfo);

    CountryCardContraintLayoutBarrier(augusteInfo)
}
