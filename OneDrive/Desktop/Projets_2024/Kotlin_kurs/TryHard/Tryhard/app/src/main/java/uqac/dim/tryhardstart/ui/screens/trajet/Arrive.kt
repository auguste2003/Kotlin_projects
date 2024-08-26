package uqac.dim.tryhardstart.ui.screens.trajet
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.amaranth
import uqac.dim.tryhardstart.ui.theme.poppins
import uqac.dim.tryhardstart.viewmodel.Trajet

@Composable
fun Arrive(onUpdate:(Boolean)->Unit,trajet: Trajet){
    var toggle by remember {
        mutableStateOf(false)
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(9.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Card(
            modifier = Modifier.size(40.dp),
            colors = CardDefaults.cardColors(
                containerColor = Green.copy(0.13f)
            )
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Icon(
                    painter = painterResource(id = R.drawable.outline_location_on_24),
                    contentDescription =null ,
                    tint = Green
                )
            }

        }
        Column {
            Row {

                Text(
                    text = trajet.villeArrive,
                    color = Green,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (screenWidth > 400) 15.sp else 13.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_expand_more_24),
                    contentDescription = null,
                    tint = Green,
                    modifier = Modifier.clickable { onUpdate(!toggle); toggle =!toggle  })
            }
            Text(text =trajet.dateDepart, fontFamily = amaranth,color = Color.LightGray , fontSize = if (screenWidth<400) 12.sp else 15.sp)
        }

    }
}