package uqac.dim.tryhardstart.ui.screens.recherche

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import uqac.dim.tryhardstart.viewmodel.AdminViewModel
import uqac.dim.tryhardstart.viewmodel.BusinessAccountViewModel
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel

// Bearbeitung und Auswahl der Buchung
@Composable

fun Personnalisation(navController: NavController,businessAccountViewModel: BusinessAccountViewModel,rechercheViewModel: RechercheViewModel,adminViewModel: AdminViewModel){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Column (
            modifier = Modifier
                .padding(top = 10.dp, start = 5.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            // Passagiere , Typ , Klassisch
            Passenger()
            // Datum hinzufügen
            Date(businessAccountViewModel,adminViewModel,rechercheViewModel)
            // Suchbutton
            ButtonSearch(navController,rechercheViewModel)

        }
    }
}