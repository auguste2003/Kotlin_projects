package uqac.dim.tryhardstart.ui.screens.codeotp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.amaranth
import uqac.dim.tryhardstart.ui.theme.aptos
import uqac.dim.tryhardstart.ui.theme.arial
import uqac.dim.tryhardstart.ui.theme.monsterart
import uqac.dim.tryhardstart.ui.theme.poppins

// Verifizierung der OPT
@Composable
fun Header(){
    Column (
        modifier = Modifier.fillMaxHeight(0.55f),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),

        ) {
            Icon(
                modifier = Modifier.padding(start = 10.dp),
                painter = painterResource(id = R.drawable.baseline_arrow_back_24) ,
                contentDescription =null )
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Verifizierung",
                fontFamily = arial,
                fontWeight = FontWeight.SemiBold,
                fontSize = 25.sp,
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.pana_ori),
                contentDescription =null
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "Geben Sie Ihr OTP ein ", color = Green,modifier = Modifier.fillMaxWidth(),fontFamily = arial, textAlign = TextAlign.Center, fontSize = 28.sp)
            Text(text = "Ein 4-stelliger Code wurde an gesendet", fontFamily = arial,color = Color.Black.copy(0.5f),modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            Text(text = "+491771537507", fontWeight = FontWeight.Bold, fontFamily = poppins,modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }

    }
}