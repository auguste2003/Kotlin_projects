package uqac.dim.tryhardstart.ui.screens.user

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.screens.agence.ajouterBus.Matricule
import uqac.dim.tryhardstart.ui.screens.user.compte.Compte
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.arial
import uqac.dim.tryhardstart.viewmodel.BusinessAccountViewModel
import uqac.dim.tryhardstart.viewmodel.SignupViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Options(navController: NavController,signupViewModel: SignupViewModel,businessAccountViewModel: BusinessAccountViewModel){
    val verify by businessAccountViewModel.compteBusinessVerify.collectAsState()
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Log.d("bool", verify.toString())
        Row(
            modifier = Modifier
                .clickable {
                    FirebaseAuth
                        .getInstance()
                        .signOut()
                    if (FirebaseAuth.getInstance().currentUser == null) {
                        navController.navigate("Login") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                    businessAccountViewModel.setCompteBusinessVerify(false)
                    signupViewModel.signOutUser(navController)

                }
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = null
                )
                Text(
                    text = "Parameter",
                    fontWeight = FontWeight.Bold,
                    fontFamily = arial
                )
            }
            Icon(
                modifier = Modifier.size(35.dp),
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription = null
            )
        }
        if (!verify){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Row(
                    modifier = Modifier.clickable {
                        navController.navigate("AjouterAgence")
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Card (
                        elevation =CardDefaults.cardElevation(
                            defaultElevation = 15.dp
                        ),
                        modifier = Modifier.size(30.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Orange
                        )
                    ){
                        Box (
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Icon(
                                tint = Color.White,
                                painter = painterResource(id = R.drawable.baseline_business_center_24),
                                contentDescription = null
                            )
                        }

                    }

                    Text(
                        text = "Haben Sie ein Resibüro?",
                        fontWeight = FontWeight.Bold,
                        fontFamily = arial
                    )
                }
                Icon(
                    modifier = Modifier.size(35.dp),
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                    contentDescription = null
                )
            }
        }
        else{
            // Benutzermodus oder Reisebüro Modus
            Compte(businessAccountViewModel)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_support_agent_24),
                    contentDescription = null
                )
                Text(
                    text = "Support",
                    fontWeight = FontWeight.Bold,
                    fontFamily = arial
                )
            }
            Icon(
                modifier = Modifier.size(35.dp),
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription = null
            )
        }
        val routeActuelle = navController.currentDestination?.route

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.clickable {
                    FirebaseAuth.getInstance().signOut()
                    Log.d("Route",routeActuelle.toString())
                    signupViewModel.signOutUser(navController)
                    navController.navigate("Login")
                    Log.d("Route",routeActuelle.toString())

                },
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Card (
                    elevation =CardDefaults.cardElevation(
                        defaultElevation = 15.dp
                    ),
                    modifier = Modifier.size(30.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Green
                    )
                ){
                    Box (
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            tint = Color.White,
                            painter = painterResource(id = R.drawable.baseline_logout_24),
                            contentDescription = null
                        )
                    }}
                Text(
                    text = "abmelden",
                    fontWeight = FontWeight.Bold,
                    fontFamily = arial
                )
            }
            Icon(
                modifier = Modifier.size(35.dp),
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription = null
            )
        }
    }

}