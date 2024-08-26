package uqac.dim.tryhardstart.ui.screens.trajet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect


class TicketShape(
    private val circleRadius: Dp,
    private val cornerSize: CornerSize
) : Shape {

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Generic(path = getPath(size, density))
    }

    private fun getPath(size: Size, density: Density): Path {
        val roundedRect = RoundRect(size.toRect(), CornerRadius(cornerSize.toPx(size, density)))
        val roundedRectPath = Path().apply { addRoundRect(roundedRect) }
        return Path.combine(operation = PathOperation.Intersect, path1 = roundedRectPath, path2 = getTicketPath(size, density))
    }

    private fun getTicketPath(size: Size, density: Density): Path {
        val middleX = size.width.div(other = 2)
        val circleRadiusInPx = with(density) { circleRadius.toPx() }
        return Path().apply {
            reset()
            // Ensure we start drawing line at top left
            lineTo(x = 0F, y = 0F)
            // Draw line to top middle
            lineTo(middleX-circleRadiusInPx, y = 0F)
            // Draw top cutout
            arcTo(
                rect = Rect(
                    left = middleX.minus(circleRadiusInPx),
                    top = 0F.minus(circleRadiusInPx),
                    right = middleX.plus(circleRadiusInPx),
                    bottom = circleRadiusInPx,
                ),
                startAngleDegrees = 180F,
                sweepAngleDegrees = -180F,
                forceMoveTo = false
            )
            // Draw line to top right
            lineTo(x = size.width, y =0F)
            // Draw line to bottom right
            lineTo(x = size.width, y = size.height)
            // Draw line to bottom middle
            lineTo(middleX, y = size.height)
            // Draw bottom cutout
            arcTo(
                rect = Rect(
                    left = middleX.minus(circleRadiusInPx),
                    top = size.height - circleRadiusInPx,
                    right = middleX.plus(circleRadiusInPx),
                    bottom = size.height.plus(circleRadiusInPx)
                ),
                startAngleDegrees = 0F,
                sweepAngleDegrees = -180F,
                forceMoveTo = false
            )
            // Draw line to bottom left
            lineTo(x = 0F, y = size.height)
            // Draw line back to top left
            lineTo(x = 0F, y = 0F)
        }
    }
}