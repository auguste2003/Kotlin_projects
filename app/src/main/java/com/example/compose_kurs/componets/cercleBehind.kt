package com.example.compose_kurs.componets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun circulerText(text:String){
    Text(text="?", modifier = Modifier
        .fillMaxWidth(0.1f).drawBehind {
            drawCircle(color = Color.LightGray, radius = this.size.maxDimension)
        }
        .padding(0.5.dp),
        fontSize=15.sp,
        textAlign = TextAlign.Center)
}