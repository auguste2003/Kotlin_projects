package uqac.dim.tryhardstart.ui.screens.trajet

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uqac.dim.tryhardstart.ui.theme.poppins
import uqac.dim.tryhardstart.viewmodel.AdminViewModel
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel

// Dieses Komponent listet alle verfügbaren Verbindungen
@Composable
fun Trajet(rechercheViewModel: RechercheViewModel,navController: NavController,adminViewModel: AdminViewModel){
    LaunchedEffect(Unit){
        rechercheViewModel.rechercherTrajets()
    }
    val trajets by rechercheViewModel.trajets.collectAsState()
    var expanded by remember {
        mutableStateOf(false)
    }
    if (trajets.isNotEmpty()){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Head od the ticke t
            Entete(rechercheViewModel,trajets)
            LazyColumn {
                items(trajets){trajet->
                    rechercheViewModel.villeArriveTrajet.value = trajet.villeArrive
                    rechercheViewModel.villeDepartTrajet.value = trajet.villeDepart
                    rechercheViewModel.matriculeTrajet.value = trajet.idBus
                    rechercheViewModel.promotionTrajet.value = trajet.promotion
                    rechercheViewModel.prixTrajet.value = trajet.prix
                    rechercheViewModel.heureFinTrajet.value = trajet.heureArrivee
                    rechercheViewModel.heureDebutTrajet.value = trajet.heureDepart
                    Box {
//               rechercheViewModel.currentBusId.value = trajet.idBus
//               adminViewModel.currentBusId.value = trajet.idBus
                        Cadre(trajet,rechercheViewModel, adminViewModel)
                        CadreFinal(trajet,navController,rechercheViewModel,adminViewModel)
                    }
                    Log.d("idBu",trajet.idBus)
                    Log.d("matri",adminViewModel.matricule.value)

                }
            }
        }
    }
    else{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Kein Ergebnis", fontSize = 40.sp, fontFamily = poppins, fontWeight = FontWeight.Bold)
        }
    }
    
}