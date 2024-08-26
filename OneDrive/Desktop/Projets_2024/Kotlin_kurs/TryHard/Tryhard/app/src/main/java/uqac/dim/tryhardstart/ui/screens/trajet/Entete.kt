package uqac.dim.tryhardstart.ui.screens.trajet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.amaranth
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel
import uqac.dim.tryhardstart.viewmodel.Trajet

// Die schöne Zeile oben auf dem Ticket
@Composable
fun Entete(rechercheViewModel: RechercheViewModel, trajet: List<Trajet>){

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .background(Green, RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24) ,
                contentDescription =null, tint = Color.White )
            Icon(
                painter = painterResource(id = R.drawable.baseline_filter_list_24) ,
                contentDescription =null,
                tint = Color.White
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
             horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = rechercheViewModel.villeDepart.value, fontSize = 25.sp, color = Color.White,fontWeight = FontWeight.Bold, fontFamily = amaranth)
            Icon(painter = painterResource(id = R.drawable.baseline_swap_horiz_24), modifier = Modifier.size(30.dp), contentDescription = null, tint = Color.White)
            Text(text = rechercheViewModel.villeArrivee.value, fontSize = 25.sp, fontWeight = FontWeight.Bold, fontFamily = amaranth, color = Color.White)
        }

    }
}