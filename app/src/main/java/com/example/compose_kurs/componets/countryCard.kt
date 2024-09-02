package com.example.compose_kurs.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_kurs.R
import com.example.compose_kurs.data.CountryInfo


@Composable
fun CountryCard(countryInfo: CountryInfo){
    Surface(modifier = Modifier
        .fillMaxWidth(1.0f)
        .padding(10.dp)
        .wrapContentHeight(align = Alignment.Top)
        .border(1.dp, Color.LightGray)) // Aligner en haut


    {
        Row(modifier = Modifier.fillMaxWidth()){
            Column (modifier = Modifier
                .fillMaxWidth(0.2f)
                .weight(0.2f)){
                Box(modifier = Modifier
                    .width(100.dp)
                    .height(80.dp)
                    .padding(2.dp) , contentAlignment = Alignment.Center){
                    val imageResOd = countryInfo.flagId
                    val imagePainter: Painter = painterResource(id= imageResOd )

                    Image(painter = imagePainter ,
                        contentDescription = "Auguste Image " ,
                        contentScale = ContentScale.Crop )
                }

                Text(text=countryInfo.commonName, modifier = Modifier
                    .fillMaxWidth(1.0f)
                    .padding(2.dp),
                    fontSize=20.sp,
                    textAlign = TextAlign.Start)
                Text(text=countryInfo.region, modifier = Modifier
                    .fillMaxWidth(1.0f)
                    .padding(2.dp),
                    fontSize=15.sp,
                    textAlign = TextAlign.Start)
            }
            Column(modifier = Modifier
                .fillMaxWidth(0.8f)
                .weight(0.2f)) {
                Text(text=countryInfo.subRegion, modifier = Modifier
                    .fillMaxWidth(1.0f)
                    .padding(2.dp),
                    fontSize=20.sp,
                    textAlign = TextAlign.Center)
                Text(text=countryInfo.region, modifier = Modifier
                    .fillMaxWidth(1.0f)
                    .padding(2.dp),
                    fontSize=15.sp,
                    textAlign = TextAlign.Center)
                Text(text=countryInfo.subRegion, modifier = Modifier
                    .fillMaxWidth(1.0f)
                    .padding(2.dp),
                    fontSize=15.sp,
                    textAlign = TextAlign.Center)
                Row(modifier = Modifier.fillMaxWidth(1.0f),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically) {

                    circulerText(text = "t") // le cercle avec un point d'intérogation au milieu

                    Text(text=countryInfo.currencySymbol, modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .padding(2.dp),
                        fontSize=15.sp,
                        textAlign = TextAlign.Center)

                    Column(modifier = Modifier.fillMaxWidth(0.4f),
                        horizontalAlignment = Alignment.End) {

                        Text(text=countryInfo.mobileCode, modifier = Modifier,
                            textAlign = TextAlign.Center)

                        Text(text=countryInfo.tld, modifier = Modifier,
                            textAlign = TextAlign.Center)
                    }

                }
            }
        }

    }

}