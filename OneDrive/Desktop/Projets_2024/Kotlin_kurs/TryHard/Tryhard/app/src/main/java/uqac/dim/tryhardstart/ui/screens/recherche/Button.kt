package uqac.dim.tryhardstart.ui.screens.recherche

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.poppins
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel

@Composable

fun ButtonSearch(navController: NavController,rechercheViewModel: RechercheViewModel){
   Button(
       onClick = {
           if (!rechercheViewModel.isAdresseValid()){
               rechercheViewModel.isAdresseValid()
           }
           else{
               rechercheViewModel.rechercherTrajets()
               navController.navigate("Fahrt")
           }
                 },
       colors = ButtonDefaults.buttonColors(
           containerColor = Green
       ),
       shape = RoundedCornerShape(9.dp),
       modifier = Modifier
           .fillMaxWidth()
           .padding(end = 5.dp)
       ) {
       Log.d("depart",rechercheViewModel.villeDepart.value)
       Log.d("arrive",rechercheViewModel.villeArrivee.value)
       Text(
           modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
           text = "Suchen",
           fontWeight = FontWeight.Bold,)
   }
    if (rechercheViewModel.textValidError.value.isNotEmpty()){
        Text(
            text = rechercheViewModel.textValidError.value ,
            fontFamily = poppins,
            color = Color.Red
        )
    }
}