package uqac.dim.tryhardstart.ui.screens.inscriptionPage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel
import uqac.dim.tryhardstart.viewmodel.SignupViewModel
// Registrierungsseite
@Composable
fun InscriptionPage(navController: NavController, signupViewModel: SignupViewModel,rechercheViewModel: RechercheViewModel){
    val context = LocalContext.current
    val isLoading by signupViewModel.isLoading.collectAsState()
    LazyColumn(
        modifier = Modifier.padding(horizontal = 0.dp)
    ) {
        Log.d("progessvalue", signupViewModel.isLoading.value.toString())
        Log.d("progessvalue1", isLoading.toString())



        item {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column {
                    EnteteInscription()
                    Formulaire(signupViewModel,navController)
                }
                if (isLoading){
                    LaunchedEffect(true){
                        rechercheViewModel.playSound(context, R.raw.traitonsconnexion)
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 10.dp)
                            .fillParentMaxSize()
                            .background(Color.Black.copy(alpha = 0.3f))
                            .pointerInput(Unit) {
                                // Capture et consomme tous les événements de pointage pour désactiver les interactions
                                awaitPointerEventScope {
                                    while (true) {
                                        awaitPointerEvent()
                                    }
                                }
                            }
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = Green
                        )
                    }
                }



            }
        }
        item {

        }
    }
}