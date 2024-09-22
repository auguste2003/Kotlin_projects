package com.example.finddog.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.finddog.DogViewModel.DogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogDetail(dogViewModel: DogViewModel, navController: NavController) {
    var dogResult = dogViewModel.selectedDog
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp),
        topBar = {

            TopAppBar(title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically, // Alligner les deux verticlement
                    modifier = Modifier.fillMaxWidth() // Remplire la larger disponible
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Dog 's details",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp, // á ajuster
                        color = Color.Black
                    )
                }

            })
        }) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp)
                        .height(200.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = PurpleGrey80
                    )
                ) {

                    AsyncImage(
                        model = dogResult?.url,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight(),
                    )

                }

                Row(
                    modifier = Modifier
                        .padding(top = 15.dp, start = 40.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Height : ${dogResult?.height}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp, // á ajuster
                        color = Color.Black
                    )
                    Text(
                        text = "Height : ${dogResult?.width}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp, // á ajuster
                        modifier = Modifier.padding(start = 6.dp), // Espace avec le texte du haut
                        color = Color.Black
                    )
                }
                Text(
                    text = " A dog with a height : ${dogResult?.height} ist going to help you so much in your " +
                            "daily activities , providing you so much fun as possible . Just follow uns in our website to " +
                            "get more informationen obout it  ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, // á ajuster
                    color = Color.Black
                )

            }
        }
    }
}


