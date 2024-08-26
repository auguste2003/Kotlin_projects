package uqac.dim.tryhardstart.ui.screens.ticket

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uqac.dim.tryhardstart.ui.theme.poppins
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel

// Informationen über den Passagier und über den QRCode
@Composable
fun Passager(rechercheViewModel: RechercheViewModel){
    Column(
        modifier = Modifier.padding(vertical = 10.dp,)
    ){
        Column {
            Text(text = "Passagier:")
            Text(text = rechercheViewModel.nomReservateur.value, fontWeight = FontWeight.Bold, fontFamily = poppins)
        }
        Column {
            Text(text = "ID:")
            Text(text = rechercheViewModel.numeroTelephone.value, fontWeight = FontWeight.Bold, fontFamily = poppins)
        }
    }
    Canvas(modifier = Modifier.size(width = 350.dp, height = 50.dp)) {
        val path = Path().apply {
            moveTo(10f, size.height / 2)
            lineTo(size.width - 10f, size.height / 2)
        }

        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

        drawPath(
            path = path,
            color = Color.LightGray,
            style = Stroke(width = 4.dp.toPx(), pathEffect = pathEffect)
        )
    }

}