package uqac.dim.tryhardstart.ui.screens.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.amaranth
import uqac.dim.tryhardstart.ui.theme.arial
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel

// Foto Name und Telefonnummer
@Composable
fun Profile(rechercheViewModel: RechercheViewModel){
    Column(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 20.dp)
    ) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription =null
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                Text(
                    text = "Mein Profil",
                    fontFamily = arial,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,

                    )
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box {
                Image(
                    modifier = Modifier
                        .size(125.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.imageauguste),
                    contentDescription =null )
                Card(
                   elevation = 20.dp,
                    modifier = Modifier
                        .size(35.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = (5).dp, y = (-3).dp),
                    shape = CircleShape
                ) {
                    Box (
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            modifier = Modifier
                                .size(25.dp),
                            tint = Orange,
                            painter = painterResource(id = R.drawable.baseline_add_a_photo_24),
                            contentDescription =null )
                    }

                }
                

            }
            Column {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = rechercheViewModel.nomReservateur.value,
                        fontWeight = FontWeight.Bold,
                        fontFamily = amaranth,
                        fontSize = 20.sp
                        )
                    Text(
                        text = rechercheViewModel.numeroTelephone.value,
                        fontFamily = arial,
                        fontSize = 16.sp
                        
                    )
                }
                Button(
                    modifier = Modifier.padding(top = 15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Green,
                        contentColor = Color.White
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "Profil bearbeiten",
                        color = Color.White,
                        fontFamily = arial
                    )
                    
                }
            }
        }
    }

}