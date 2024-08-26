package uqac.dim.tryhardstart.ui.screens.inscriptionPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.amaranth
import uqac.dim.tryhardstart.ui.theme.aptos
import uqac.dim.tryhardstart.ui.theme.arial
import uqac.dim.tryhardstart.ui.theme.monsterart
import uqac.dim.tryhardstart.ui.theme.poppins

// Der obene Block der registrierungsseite , die die Icons für die Anmeldung enthält
@Composable
fun EnteteInscription(){
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                modifier = Modifier.padding(top = 10.dp),
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = null)
            Image(
                modifier = Modifier
                    .height(125.dp)
                    .fillMaxWidth()
                ,
                painter = painterResource(id = R.drawable.loginpage) ,
                contentDescription =null )

        }
        @Composable
        fun CountryCodePickerFunction() {
            var enteredText by remember {
                mutableStateOf("")
            }
            Column(
                modifier = Modifier.padding(0.dp).fillMaxWidth(1f),
            ) {

            }
        }
        // Aufruf der lokalen Fubktion
        CountryCodePickerFunction()
        Column {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Orange,
                text = "Registrierung",
                fontFamily = arial,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp)
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Geben Sie bitte Ihre Informationen ein !",
                fontFamily = arial,
            )
        }

    }

}