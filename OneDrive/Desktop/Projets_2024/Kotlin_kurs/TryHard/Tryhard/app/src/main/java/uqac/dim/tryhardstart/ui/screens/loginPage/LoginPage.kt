package uqac.dim.tryhardstart.ui.screens.loginPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.poppins
import uqac.dim.tryhardstart.ui.theme.roboto
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel
import uqac.dim.tryhardstart.viewmodel.SignupViewModel

@Composable
fun LoginPage(navController: NavController,signupViewModel: SignupViewModel,rechercheViewModel: RechercheViewModel){
    val isLoading by signupViewModel.isLoading.collectAsState()
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = R.drawable.loginpage) ,
                contentDescription = null )
            Forms(navController,signupViewModel,rechercheViewModel)
        }
        if (isLoading){
             LaunchedEffect(true){
                 rechercheViewModel.playSound(context,R.raw.rush)
             }
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding()
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .pointerInput(Unit) {
                        // Capture et consomme tous les événements de pointage pour désactiver les interactions
                        awaitPointerEventScope {
                            while (true) {
                                awaitPointerEvent()
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Box (
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(Color.White)
                    ,
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        modifier = Modifier.padding(17.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .size(40.dp)
                                .align(Alignment.CenterHorizontally),
                            backgroundColor = Orange,
                            strokeCap = StrokeCap.Square,
                            color = Green
                        )
                        Text(text = "Wir bearbeiten Ihre Informationen", modifier = Modifier
                            .width(250.dp)
                            .padding(10.dp), fontFamily = poppins, fontWeight = FontWeight.Bold, fontSize = 22.sp, textAlign = TextAlign.Center)
                        Text(text = "Das kann einige Sekunden nehemen", modifier = Modifier.width(250.dp), textAlign = TextAlign.Center, fontFamily = poppins)
                    }

                }


            }
        }

    }

}