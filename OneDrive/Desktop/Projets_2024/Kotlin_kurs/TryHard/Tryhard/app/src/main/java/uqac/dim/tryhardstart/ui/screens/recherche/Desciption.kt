package uqac.dim.tryhardstart.ui.screens.recherche

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import uqac.dim.tryhardstart.ui.theme.amaranth
import uqac.dim.tryhardstart.ui.theme.arial
import uqac.dim.tryhardstart.ui.theme.poppins
import uqac.dim.tryhardstart.viewmodel.BusinessAccountViewModel
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel

@Composable
fun Desciption(businessAccountViewModel: BusinessAccountViewModel,rechercheViewModel: RechercheViewModel){
    val modeUser by businessAccountViewModel.modeUser.collectAsState()
    Column {
        Text(text = "Hallo, " + rechercheViewModel.nomReservateur.value, color = Color.White, fontFamily = arial)

        Text(
            text = if ((modeUser)) "Buchen Sie Ihr Ticket" else "Starten Sie den Betrieb eines Busses",
            color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold, fontFamily = amaranth)
    }

}