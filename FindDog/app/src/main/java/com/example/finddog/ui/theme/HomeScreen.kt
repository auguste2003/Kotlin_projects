package com.example.finddog.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.finddog.DogViewModel.DogViewModel
import com.example.finddog.DogViewModel.Status
import com.example.finddog.data.remote.RetrofitInstance.kt.DogResponceItem


@Composable
fun HomeScreen(dogViewModel: DogViewModel , navController: NavController){

     var status = dogViewModel.status.observeAsState()

   when(status.value){
      Status.loading -> CircularProgressIndicator()
       Status.error  ->  ErrorMessage()
       Status.success -> showAllDogs(dogViewModel,navController)
       null -> TODO()
   }
}
@Composable
fun ErrorMessage(){
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 50.dp,top = 200.dp)) {
        Text(text = "An Error Occured when getting the datas ", textAlign = TextAlign.Center, color = Color.Red )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showAllDogs(dogViewModel: DogViewModel,navController: NavController){
    var dogResult  = dogViewModel._dogResult.observeAsState()

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        topBar = {
                 TopAppBar(
                     title = {
                         Text(
                             text = "Find your dogs",
                             textAlign = TextAlign.Center,
                             color = Color.Black ,
                             fontWeight = FontWeight.Bold
                         )
                     },
                     actions = {
                         IconButton(onClick = { /*TODO*/ }) {
                             Icon(Icons.Default.Settings, contentDescription =null )
                         }
                     }
                 )
        },
        bottomBar = {
            // ButtomBar
        }

    ){innerPaddings ->
        Column( modifier = Modifier.padding(innerPaddings)) {
           LazyColumn (modifier = Modifier.fillMaxWidth()){
               item {
                   dogResult.value?.forEach {
                       DogItem(it,navController, viewModel = dogViewModel)  // Afficher chaque carte
                   }
               }
           }


        }

    }

}

@Composable
fun DogItem(dog: DogResponceItem,navController: NavController,viewModel: DogViewModel){

    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(3.dp)
        .height(200.dp)
        .clickable {
            viewModel.selectedDog = dog
            navController.navigate("dogDetails")
        }
        .clip(RoundedCornerShape(6.dp)),
        colors = CardDefaults.cardColors(
            containerColor = PurpleGrey80
        )
        ) {

        Row(modifier = Modifier.fillMaxSize()){

            AsyncImage(model =dog.url
                , contentDescription =null
                , modifier = Modifier.weight(0.6f).
                fillMaxHeight(),
                )
            Column(modifier = Modifier
                .weight(0.4f)
                .padding(start = 8.dp)
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Height : ${dog.height}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, // á ajuster
                    color = Color.Black)
                Text(text = "Height : ${dog.width}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, // á ajuster
                    modifier = Modifier.padding(top = 4.dp), // Espace avec le texte du haut
                    color = Color.Black)
            }
        }


    }

}