package uqac.dim.tryhardstart.ui.screens.place

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import uqac.dim.tryhardstart.ui.screens.trajet.Entete
import uqac.dim.tryhardstart.ui.theme.Green
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.amaranth
import uqac.dim.tryhardstart.ui.theme.monsterart
import uqac.dim.tryhardstart.ui.theme.poppins
import uqac.dim.tryhardstart.ui.theme.roboto
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel
import uqac.dim.tryhardstart.viewmodel.Trajet

// In diesem Komponent kann man ein Platz insgesamt aussuchen
@Composable
fun Place(rechercheViewModel: RechercheViewModel,navController: NavController){
    LaunchedEffect(Unit){
        rechercheViewModel.tikectBook()
    }
    Column {
        Box {
            EntetePlace(rechercheViewModel)
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(90.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = 40.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = "Auguste Sonfack", fontFamily = poppins, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Typ:")
                            Text(text = " VIP(2 Tickets)", color = Orange, fontWeight = FontWeight.Bold, fontFamily = amaranth)
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly,

                    ) {
                        Text(text = rechercheViewModel.heureDebutTrajet.value,fontFamily = poppins, fontWeight = FontWeight.Bold)
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Preis: ")
                            Text(text = rechercheViewModel.prixTrajet.value+"F",fontFamily = amaranth, fontWeight = FontWeight.Bold, color = Color.Black.copy(0.7f))
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 50.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    ),
                    modifier = Modifier.size(20.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                }
                Text(text = " Verfügbar ", )
            }
            Row {
                Card(
                    modifier = Modifier.size(20.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Orange
                    )
                ) {
                }
                Text(text = " Ausgewählt")
            }
            Row {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Green
                    )
                ) {
                }
                Text(text = " Reservieren")
            }
        }
        Box (modifier = Modifier.fillMaxSize()){
            BusPath()
            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = (-50).dp, y = 90.dp)
                        ,
                        painter = painterResource(id = R.drawable.volant_de_voiture) ,
                        contentDescription = null)


                }

            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(390.dp)
                    .offset(y = 30.dp)
                    //.padding(top = 40.dp)
            ){
                NumeroPlace(rechercheViewModel )
            }
            if (rechercheViewModel.bouttonPayer.value){
                Button(
                    onClick = {
                        //rechercheViewModel.tikectBook()
                            rechercheViewModel.updateSeatStatus()
                            rechercheViewModel.updateSeatStatusGauche()
                        navController.navigate("Ticket")


                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Orange
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = -64.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                ) {
                    Text(text = "Jetzt bezahlen", fontFamily = amaranth)
                }
            }

            Log.d("idvaleur",rechercheViewModel.numeroChaise.value)
            Log.d("idpremier",rechercheViewModel.currentBusId.value)
        }
    }


}
