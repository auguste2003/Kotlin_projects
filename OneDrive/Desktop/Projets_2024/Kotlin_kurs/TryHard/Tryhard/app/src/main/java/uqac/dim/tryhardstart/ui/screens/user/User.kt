package uqac.dim.tryhardstart.ui.screens.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import uqac.dim.tryhardstart.viewmodel.BusinessAccountViewModel
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel
import uqac.dim.tryhardstart.viewmodel.SignupViewModel

// Das Komponent enthält alle Informationen relativ zu dem Nutzer
@Composable
fun User(navController: NavController,signupViewModel: SignupViewModel,businessAccountViewModel: BusinessAccountViewModel,rechercheViewModel: RechercheViewModel){
    Column {
        // Bild Name und Telefonnummer
        Profile(rechercheViewModel)
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
            thickness = 2.dp
        )
        Spacer(
            modifier = Modifier
                .height(15.dp)
                )
        // Parameter , Modus , Support , Abmeldung
        Options(navController,signupViewModel,businessAccountViewModel)
    }
}