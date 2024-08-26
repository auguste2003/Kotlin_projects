package uqac.dim.tryhardstart.ui.screens.ticket

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.Orange

@Composable
fun ProgressBar(){
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFFFF7420), Color(0xFF1BAF1B)),
        start = Offset.Zero, // Vous pouvez ajuster ces valeurs pour changer la direction du dégradé
        end = Offset.Infinite
    )
    Canvas(modifier = Modifier.size(width = 300.dp, height = 50.dp)) {
        val strokeWidth = 6.dp.toPx()
        val circleRadius = strokeWidth * 2
        val startColor = Color.Red
        val endColor = Color.Black
        val stroke = Stroke(width = 4.dp.toPx())

        // Dessinez la ligne de la barre de progression
        drawLine(
            brush = gradient,
            //color = startColor,
            start = Offset(circleRadius*2, size.height / 2),
            end = Offset(size.width - circleRadius*2, size.height / 2),
            strokeWidth = strokeWidth,
        )

        // Dessinez le cercle de départ
        drawCircle(
            color = Orange,
            radius = circleRadius,
            center = Offset(circleRadius, size.height / 2),
            style = stroke
        )

        // Dessinez le cercle de fin
        drawCircle(
            color = Green,
            radius = circleRadius,
            center = Offset(size.width - circleRadius, size.height / 2),
            style = stroke
        )
    }

}
