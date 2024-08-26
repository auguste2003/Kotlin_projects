package uqac.dim.tryhardstart.ui.screens.bottomNavigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.amaranth
import uqac.dim.tryhardstart.ui.theme.poppins

// Dieses Component enthält alle Buttons der unteren Navigationsleiste
@Composable
fun BottomNavigationBar(navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        Screen.Home, // 'Accueil'
        Screen.Trajet, //  'Trajet '
        Screen.Ticket, //  'Ticket '
        Screen.Profile //  'User'
    )
   BottomNavigation(
       backgroundColor = Color.White,
       elevation = 20.dp,
       contentColor = Color.Black
   ) {
       items.forEach {
           screen ->
           BottomNavigationItem(
               selectedContentColor = Orange,
               unselectedContentColor = Color.Black.copy(0.4f),
               selected = currentRoute==screen.route ,
               onClick = {
                         if(currentRoute!=screen.route){
                             navController.navigate(screen.route){ // Naviegiere zu dem ausgewählten Ort
                                 popUpTo(navController.graph.startDestinationId)
                                 launchSingleTop = true
                             }
                         }
               },
               label = { Text(text = screen.title, fontFamily = amaranth, fontWeight = FontWeight.Bold)},
               icon = {
                   Column {
                       Box(contentAlignment = Alignment.TopCenter){
                           if(currentRoute==screen.title){
                               Box(modifier = Modifier
                                   .fillMaxWidth(0.9f)
                                   .height(2.dp)
                                   .background(Orange))
                           }
                           Icon(painter = painterResource(id = screen.icon), contentDescription = null, modifier = Modifier.padding(top = 6.dp))

                       }

                   }
                   }

           )
           Log.d("route",currentRoute.toString())

       }

   }


}