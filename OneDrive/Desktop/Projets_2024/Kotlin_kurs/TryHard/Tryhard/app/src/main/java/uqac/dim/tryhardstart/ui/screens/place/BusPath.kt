package uqac.dim.tryhardstart.ui.screens.place

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.dp
import uqac.dim.tryhardstart.ui.theme.Green

@Composable

fun BusPath(){
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(15.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val topLeft = Offset(x = size.width / 900, y = size.height / 4)
        // Création d'un chemin pour la forme de base du bus
        val busPath = Path().apply {
            // Dessine le rectangle de base
            val rect = Rect(
                left = 0f,
                top = canvasHeight / 4-40f,
                right = canvasWidth,
                bottom = canvasHeight
            )

            addRect(rect)

            val rect1 = Rect(
                offset = Offset(150f, 130f),
                size = Size(canvasWidth/1-150f, canvasHeight/(7))
            )
            val rect2 = Rect(
                offset = Offset(0f, 0f),
                size = Size(canvasWidth, canvasHeight/8)
            )
            addRoundRect(
                RoundRect(
                    rect = rect1,
                    topLeft = CornerRadius(0f)
                )
            )
            addRoundRect(
                RoundRect(
                    rect = rect2,
                    topLeft = CornerRadius(140f),
                    topRight = CornerRadius(140f)
                )
            )
        }

        clipPath(path = busPath) {
            drawPath(
                path = busPath,
                color = Green.copy(0.26f) // Remplacer par la couleur de votre choix
            )
        }
    }
}