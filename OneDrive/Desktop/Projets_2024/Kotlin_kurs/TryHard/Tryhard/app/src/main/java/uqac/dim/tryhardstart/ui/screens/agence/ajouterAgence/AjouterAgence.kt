package uqac.dim.tryhardstart.ui.screens.agence.ajouterAgence

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.amaranth
import uqac.dim.tryhardstart.ui.theme.arial
import uqac.dim.tryhardstart.ui.theme.poppins
import uqac.dim.tryhardstart.viewmodel.BusinessAccountViewModel

@Composable
fun AjouterAgence(navController: NavController, businessAccountViewModel: BusinessAccountViewModel = viewModel()) {
    val agencyName by businessAccountViewModel.nomAgence.collectAsState()
    val adresseCourriel by businessAccountViewModel.adresseCourriel.collectAsState()
    val nombreBus by businessAccountViewModel.nombreDeBus.collectAsState()
    val adresseSiegeSocial by businessAccountViewModel.adresseSiegeSocial.collectAsState()
    val complete by businessAccountViewModel.isSuccessful.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Sobald das Konto erfolgreich erstellt wurde
    LaunchedEffect(complete) {
        if (complete) {
            // Zeige die Snackbar an
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Konto erfolgreich erstellt",
                    actionLabel = "OK",
                    duration = SnackbarDuration.Short
                )
            }
            // Navigiere zur Startseite
            navController.navigate("Startseite")
            // Setze den Status zurück

        }
    }

    Column {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = null
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Buisnesskonto",
                    fontWeight = FontWeight.Bold,
                    fontFamily = arial,
                    fontSize = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Werden Sie Partner von TryHard, indem Sie dieses Formular ausfüllen",
            fontFamily = amaranth,
            color = Green,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
        ) {
            OutlinedTextField(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.padding(end = 5.dp),
                            tint = Orange,
                            painter = painterResource(id = R.drawable.baseline_person_24),
                            contentDescription = null
                        )
                        Box(
                            modifier = Modifier
                                .height(54.dp)
                                .width(2.dp)
                                .background(Color.Black.copy(0.5f))
                        )
                    }
                },
                value = agencyName,
                onValueChange = {
                    businessAccountViewModel.nomAgence.value = it
                },
                label = {
                    Text(
                        text = "Name des Reisebüros",
                        fontFamily = arial
                    )
                }
            )

            OutlinedTextField(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.padding(end = 5.dp),
                            tint = Orange,
                            painter = painterResource(id = R.drawable.baseline_email_24),
                            contentDescription = null
                        )
                        Box(
                            modifier = Modifier
                                .height(54.dp)
                                .width(2.dp)
                                .background(Color.Black.copy(0.5f))
                        )
                    }
                },
                value = adresseCourriel,
                onValueChange = {
                    businessAccountViewModel.adresseCourriel.value = it
                },
                label = {
                    Text(
                        text = "Email",
                        fontFamily = arial
                    )
                }
            )

            OutlinedTextField(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.padding(end = 5.dp),
                            tint = Orange,
                            painter = painterResource(id = R.drawable.baseline_directions_bus_24),
                            contentDescription = null
                        )
                        Box(
                            modifier = Modifier
                                .height(54.dp)
                                .width(2.dp)
                                .background(Color.Black.copy(0.5f))
                        )
                    }
                },
                value = nombreBus,
                onValueChange = {
                    businessAccountViewModel.nombreDeBus.value = it
                },
                label = {
                    Text(
                        text = "Busanzahl",
                        fontFamily = arial
                    )
                }
            )

            OutlinedTextField(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.padding(end = 5.dp),
                            tint = Orange,
                            painter = painterResource(id = R.drawable.outline_location_on_24),
                            contentDescription = null
                        )
                        Box(
                            modifier = Modifier
                                .height(54.dp)
                                .width(2.dp)
                                .background(Color.Black.copy(0.5f))
                        )
                    }
                },
                value = adresseSiegeSocial,
                onValueChange = {
                    businessAccountViewModel.adresseSiegeSocial.value = it
                },
                label = {
                    Text(
                        text = "Adresse des Firmensitzes",
                        fontFamily = arial
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                tint = Green,
                painter = painterResource(id = R.drawable.baseline_check_circle_24),
                contentDescription = null
            )
            Text(text = "Ich akzeptiere die ")
            Text(
                text = "Allgemeinen Geschäftsbedingungen",
                color = Orange,
                fontFamily = arial
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                businessAccountViewModel.enregistrementCompteBusiness()
            }
        ) {
            Text(text = "Konto erstellen ")
        }

        SnackbarHost(hostState = snackbarHostState) { data ->
            Snackbar(
                snackbarData = data,
                // Anpassung des Snackbar-Layouts bei Bedarf
            )
        }
    }
}
