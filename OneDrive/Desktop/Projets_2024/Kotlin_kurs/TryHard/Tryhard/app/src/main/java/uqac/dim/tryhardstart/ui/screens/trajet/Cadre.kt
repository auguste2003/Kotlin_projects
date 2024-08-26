package uqac.dim.tryhardstart.ui.screens.trajet

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import uqac.dim.tryhardstart.viewmodel.AdminViewModel
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel
import uqac.dim.tryhardstart.viewmodel.Trajet

@Composable
fun Cadre(trajet: Trajet,rechercheViewModel: RechercheViewModel,adminViewModel: AdminViewModel){


    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val heightModifier = if (screenWidth < 200) 0.35f else 0.3f
    val paddingModifier = if (screenWidth < 400) 10.dp else 20.dp
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(9.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingModifier)
            .height(175.dp)
            .clickable {
                rechercheViewModel.currentBusId.value = trajet.idBus
                adminViewModel.currentBusId.value = trajet.idBus
            }
    ) {
        Log.d("cadreId",rechercheViewModel.currentBusId.value)
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val lineX = canvasWidth * 3 / 5  // La position de la ligne pointillée

            // Effet de ligne pointillée
            val dashPathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

            // Dessiner la ligne pointillée
            drawLine(
                color = Color.Black,
                start = Offset(lineX, 0f),
                end = Offset(lineX, canvasHeight),
                pathEffect = dashPathEffect
            )

            // Rayon des cercles de découpe
            val circleRadius = if (screenWidth<400) 20f else 40f

            // Dessiner le cercle de découpe au début de la ligne
            drawCircle(
                color = Color.LightGray,
                radius = circleRadius,
                center = Offset(lineX,-10f + circleRadius )
            )

            // Dessiner le cercle de découpe à la fin de la ligne
            drawCircle(
                color = Color.LightGray,
                radius = circleRadius,
                center = Offset(lineX,canvasHeight-8f )
            )
        }
    }
}