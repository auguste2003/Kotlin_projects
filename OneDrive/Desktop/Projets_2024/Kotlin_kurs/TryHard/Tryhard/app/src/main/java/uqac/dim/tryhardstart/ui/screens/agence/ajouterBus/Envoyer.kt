package uqac.dim.tryhardstart.ui.screens.agence.ajouterBus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uqac.dim.tryhardstart.ui.screens.recherche.Date
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.viewmodel.AdminViewModel
import uqac.dim.tryhardstart.viewmodel.BusinessAccountViewModel
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel

// Dieses Formular wird geöffnet , wenn der Nutzer eher ein Reiseburo ist
@Composable
fun Envoyer(businessAccountViewModel: BusinessAccountViewModel,adminViewModel: AdminViewModel,rechercheViewModel: RechercheViewModel){
    var completeShowFlow = MutableStateFlow(false) // Default value is true
    val completeShow by completeShowFlow.collectAsState()
    val modeUser by businessAccountViewModel.modeUser.collectAsState()
    Card(
        modifier = Modifier
            //.height(405.dp)
            //.padding(bottom = 0.dp)
        ,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {

        Column (
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 10.dp, start = 5.dp, bottom = 0.dp)
            ,
           // horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy( if(modeUser)20.dp else 8.dp)
        ){
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column {
                    // Passagiere
                    Passenger(adminViewModel)
                    // Datum auswählen
                    Date(businessAccountViewModel,adminViewModel, rechercheViewModel )
                    // Abfahrtzeit
                    HeureDepart(adminViewModel)
                    // Ankunftzeit
                    HeureArrive(adminViewModel)
                    // Matrikelnummer des Autos
                    Matricule(adminViewModel)
                    // Formular mit Prize und Promition
                    Formulaire(adminViewModel)
                    Button(
                        onClick = {
                            completeShowFlow.value =true
                            adminViewModel.saveDataToFirestore()
                        },
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Green
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 3.dp)) {
                        Text(text = "Speichern")
                    }
                }
                Box(
                    modifier = Modifier.align(alignment = Alignment.TopCenter)
                ) {
                    val snackbarHostState = remember { SnackbarHostState() }
                    val scope = rememberCoroutineScope()
                    LaunchedEffect(completeShow){
                        if (completeShow){

                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Bus erfolgreich hinzugefügt",
                                    actionLabel = "OK",
                                    duration = SnackbarDuration.Short
                                )
                            }
                            completeShowFlow.value = false

                        }
                    }
                    SnackbarHost(hostState = snackbarHostState) { data ->
                        Snackbar(
                            snackbarData = data,
                            modifier = Modifier.height(140.dp)
                        )
                    }
                }


            }





        }
    }

}