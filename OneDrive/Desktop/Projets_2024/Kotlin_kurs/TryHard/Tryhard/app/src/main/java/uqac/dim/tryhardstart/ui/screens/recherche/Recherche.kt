package uqac.dim.tryhardstart.ui.screens.recherche

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.screens.agence.ajouterBus.Envoyer
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.amaranth
import uqac.dim.tryhardstart.ui.theme.poppins
import uqac.dim.tryhardstart.viewmodel.AdminViewModel
import uqac.dim.tryhardstart.viewmodel.BusinessAccountViewModel
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel

// Algemeine Suche für Reisebüros und für Nutzer auch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Recherche(navController: NavController,businessAccountViewModel: BusinessAccountViewModel,rechercheViewModel: RechercheViewModel,adminViewModel: AdminViewModel){
    // Verwende collectAsState, um den aktuellen Wert von modeUser zu beobachten
    val modeUser by businessAccountViewModel.modeUser.collectAsState()
  LaunchedEffect(Unit){
      businessAccountViewModel.verificationSuccess()
      rechercheViewModel.tikectBook()
  }
    LazyColumn(
        modifier = Modifier.fillMaxSize(1f)
    ) {
        Log.d("Sehen",businessAccountViewModel.compteBusinessVerify.value.toString())
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (modeUser) 200.dp else 200.dp)
                    .background(Green, RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            start = 20.dp,
                            end = 20.dp
                        )
                        .fillMaxHeight(0.6f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Entete()
                    Desciption(businessAccountViewModel,rechercheViewModel)
                }
            }
            Column (
                modifier = Modifier.fillMaxSize()

            ){
                Box(
                ) {
                    Column(
                        modifier = Modifier
                            .zIndex(0f)
                            //.fillMaxSize()
                            .offset(y = if (modeUser) (-45).dp else (-83).dp)
                    ) {
                        // Abfaht
                        From(businessAccountViewModel,rechercheViewModel,adminViewModel)
                        Spacer(modifier = Modifier.height(5.dp))
                        // Ankünft
                        To(businessAccountViewModel,rechercheViewModel,adminViewModel)

                    }
                    // Buchung
                    Card(
                        modifier = Modifier
                            .zIndex(12f)
                            .align(Alignment.TopEnd)
                            .padding(
                                end = 45.dp,
                                top = if (modeUser) 30.dp else 0.dp
                            )
                            .offset(y = if (modeUser) 5.dp else (-14).dp)
                            .size(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Green
                        )

                    ) {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .zIndex(4f), contentAlignment = Alignment.Center){
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_swap_calls_24),
                                contentDescription =null, tint = Color.White )
                        }

                    }


                }
                Column(
                    modifier = Modifier
                        .padding(
                            horizontal = 20.dp,
                            vertical = if (modeUser) 20.dp else 0.dp
                        )
                        .offset(y = if (modeUser) (-45).dp else (-80).dp)
                ) {
                    if ((modeUser)){
                        //Utilisateur
                        Personnalisation(navController,businessAccountViewModel, rechercheViewModel,adminViewModel )
                    }
                    else{
                        // Reisebüros
                        Envoyer(businessAccountViewModel,adminViewModel,rechercheViewModel)
                    }

                }

            }
        }
    }


}