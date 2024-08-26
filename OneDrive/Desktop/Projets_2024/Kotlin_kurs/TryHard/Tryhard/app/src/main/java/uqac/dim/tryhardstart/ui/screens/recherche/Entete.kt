package uqac.dim.tryhardstart.ui.screens.recherche

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.amaranth

// Das ist der Tryhard Titel mit dem Benachrichtigungsbutton
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Entete(){
    // Der Tryhard Titel
    val title = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.White)){
            append("Try")
        }
        withStyle(style = SpanStyle(color = Color.Black)){
            append("Hard")
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 30.sp,
            fontFamily = amaranth,
            fontWeight = FontWeight.Bold)
        BadgedBox(
            badge ={ Badge(containerColor = Orange, modifier = Modifier.size(10.dp).offset(0.dp,7.dp)) { Text(text = "") } } ) {
           // BenachrichtigungsIcon
            Icon(
                painter = painterResource(id = R.drawable.baseline_notifications_24),
                contentDescription =null,
                tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.LightGray.copy(0.5f), CircleShape)
            )
        }

    }
}