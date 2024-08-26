package uqac.dim.tryhardstart

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import uqac.dim.tryhardstart.ui.screens.agence.ajouterAgence.AjouterAgence
import uqac.dim.tryhardstart.ui.screens.bottomNavigation.BottomNavigationBar
import uqac.dim.tryhardstart.ui.screens.codeotp.PageOtp
import uqac.dim.tryhardstart.ui.screens.inscriptionPage.InscriptionPage
import uqac.dim.tryhardstart.ui.screens.loginPage.LoginPage
import uqac.dim.tryhardstart.ui.screens.place.Place
import uqac.dim.tryhardstart.ui.screens.recherche.Recherche
import uqac.dim.tryhardstart.ui.screens.ticket.Ticket
import uqac.dim.tryhardstart.ui.screens.trajet.Trajet
import uqac.dim.tryhardstart.ui.screens.user.User
import uqac.dim.tryhardstart.ui.theme.TryHardStartTheme
import uqac.dim.tryhardstart.viewmodel.AdminViewModel
import uqac.dim.tryhardstart.viewmodel.BusinessAccountViewModel
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel
import uqac.dim.tryhardstart.viewmodel.SignupViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TryHardStartTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Aktuelle Benutzer speichern
                    val authState = remember { mutableStateOf(FirebaseAuth.getInstance().currentUser) }

                    // Navigation Controller
                    val navController = rememberNavController()

                    // Viewmodels
                    val signupViewModel: SignupViewModel = viewModel()
                    val rechercheViewModel:RechercheViewModel = viewModel()
                    val businessAccountViewModel:BusinessAccountViewModel = viewModel()
                    val adminViewModel:AdminViewModel = viewModel()
                    // Beacht ob die Navigationsleiste angezeigt werden sollte oder nicht
                    val showBottomNav by signupViewModel.showbottomNav.collectAsState()
                    fun setStatusBarColor(colorString: String) {
                        window.statusBarColor = android.graphics.Color.parseColor(colorString)
                    }
                    Scaffold(
                        // Scaffold ist ein Layout, das Material-Design-Komponenten wie die App-Bar, Bottom-Bar usw. unterstützt
                        bottomBar = {
                            if (!showBottomNav){
                                // Navigationsleiste
                                BottomNavigationBar(navController)

                            }
                        }
                    ) {
                     //   NavHost(navController = navController, startDestination = if(authState.value!=null) "Startseite" else "Login") {
                        NavHost(navController = navController, startDestination = "Startseite") {

                            composable("Inscription") {
                                InscriptionPage(navController,signupViewModel,rechercheViewModel)
                                signupViewModel.setShowBottomNav(true)
                            }
                            composable("codeOtp") { PageOtp(navController,signupViewModel,businessAccountViewModel) }
                            composable("Startseite") {
                                // Von der Tryhard Titel bis zu Suchen Button
                                Recherche(navController,businessAccountViewModel,rechercheViewModel,adminViewModel)
                                signupViewModel.setShowBottomNav(false)
                                setStatusBarColor("#1BAF1B")


                            }
                            composable("Benutzer"){
                                // fürht zu der Profil Seite
                                User(navController,signupViewModel,businessAccountViewModel,rechercheViewModel)
                            }
                            // Führt zu der Login Seite
                            composable("Login"){
                                LoginPage(navController,signupViewModel,rechercheViewModel)
                                signupViewModel.setShowBottomNav(true)

                            }
                            composable("AjouterAgence"){
                                // Eine Reisebüro hinzufügen
                                AjouterAgence(navController,businessAccountViewModel)
                            }
                            composable("Fahrt"){
                                // Die Verbindungen auflisten
                                Trajet(rechercheViewModel,navController,adminViewModel)
                            }
                            composable("place"){
                                // Ein Platz aussuchen
                                Place(rechercheViewModel,navController)
                                signupViewModel.setShowBottomNav(true)

                            }
                            composable("Ticket"){
                                // Informationen über das Ticket
                                Ticket(rechercheViewModel)
                            }

                        }
                        //Place()
                        //Trajet()
                        //InscriptionPage(navController)
                        //InscriptionPage()
                        //Recherche()
                    }

                    //StartPage()
                    //LoginPage()
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TryHardStartTheme {
        StartPage()
    }
}