package uqac.dim.tryhardstart.ui.screens.recherche

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.poppins

// Passagier . typ und klassisch
@Composable

fun Passenger(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            modifier = Modifier.size(60.dp),
            colors = CardDefaults.cardColors(
                containerColor = Orange.copy(0.13f)
            )
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_hail_24),
                    contentDescription =null ,
                    tint = Orange
                    )
            }

        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "PASSAGIER",fontWeight = FontWeight.ExtraBold, color = Color.LightGray)
            Row(
                horizontalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_remove_circle_outline_24) ,
                    contentDescription =null, tint = Orange.copy(0.7f) )
                Text(text = "02",fontWeight = FontWeight.ExtraBold) // Valeur en dur
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_circle_24) ,
                    contentDescription =null,
                    tint = Orange
                    )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),

        ) {
            Text(text = "TYP",fontWeight = FontWeight.Bold, color = Color.LightGray)
            Row {
                Text(text = "KLASSISCH", fontWeight = FontWeight.ExtraBold)
                Icon(
                    painter = painterResource(id = R.drawable.baseline_expand_more_24) ,
                    contentDescription = null)
            }
        }
    }
}