package uqac.dim.tryhardstart.ui.screens.trajet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.poppins

@Composable
fun Achetez (){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    Column(
        modifier = Modifier
            .offset(x = if (screenWidth>900) 15.dp else 18.dp, y = 25.dp)
            .fillMaxHeight(0.26f),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "7:30 PM", fontSize = if (screenWidth > 400) 25.sp else 15.sp, fontWeight = FontWeight.Bold)
        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.
            buttonColors(containerColor = Orange),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "bezahlen", fontFamily = poppins
            )
        }
        Row {
            Text(text = "Preis:")
            Column {
                Row(
                ) {
                    Text( text = "5500F", textDecoration = TextDecoration.LineThrough,)
                    Text(modifier = Modifier.graphicsLayer { rotationZ = -30f }.offset(x=6.dp,y= (-5).dp),text = "-25%", fontWeight = FontWeight.Bold, color = Color.Red, fontSize = 10.sp,)
                }
                Text(text = "5000F", fontWeight = FontWeight.Bold, color = Color.Red)

            }

        }

    }
}