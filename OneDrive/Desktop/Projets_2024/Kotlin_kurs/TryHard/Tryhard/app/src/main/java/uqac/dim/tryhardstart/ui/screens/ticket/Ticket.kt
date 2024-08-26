package uqac.dim.tryhardstart.ui.screens.ticket

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel

// Dieseoenent zeigt ein Tiket
@Composable
fun Ticket(rechercheViewModel: RechercheViewModel){
    Column {
        Details(rechercheViewModel)
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
            containerColor = Green),
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .fillMaxWidth()
                .height(45.dp)
            ,
        ) {
            Text(text = "Speichern")
        }
    }

}