package uqac.dim.tryhardstart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.Swipe
import uqac.dim.tryhardstart.ui.theme.amaranth
import uqac.dim.tryhardstart.ui.theme.monsterart
import kotlin.math.roundToInt

@Composable
fun SwipeButton(onSwiped: () -> Unit) {
    val swipeWidth = 300.dp // La largeur totale du bouton glissable
    val circleSize = 75.dp // La taille du cercle à faire glisser
    val maxOffset = with(LocalDensity.current) { swipeWidth.toPx() - circleSize.toPx() }
    val offsetX = remember { mutableFloatStateOf(0f) }
  Card(
      shape = RoundedCornerShape(27.dp)

  ) {
      Box(
          contentAlignment = Alignment.CenterStart,
          modifier = Modifier
              .fillMaxWidth()
              .height(60.dp)
              .background(Swipe.copy(0.2f))
      ) {
          Card(
              shape = RoundedCornerShape(27.dp),
              elevation = CardDefaults.cardElevation(38.dp)
          ) {
              Box(
                  modifier = Modifier
                      .offset { IntOffset(offsetX.floatValue.roundToInt(), 0) }
                      .size(circleSize)
                      .background(Orange)
                      .pointerInput(Unit) {
                          detectDragGestures { _, dragAmount ->
                              val newOffset = offsetX.floatValue + dragAmount.x
                              offsetX.floatValue = newOffset.coerceIn(0f, maxOffset)
                              if (offsetX.floatValue >= maxOffset) {
                                  onSwiped()
                              }
                          }
                      },

                  ){
                  Icon(
                      painter = painterResource(id = R.drawable.baseline_keyboard_double_arrow_right_24),
                      contentDescription =null,
                      tint = Color.White,
                      modifier = Modifier
                          .align(Alignment.Center)
                  )
              }
          }

          Text("Faites glisser pour commencer", color = Color.Black, fontWeight = FontWeight.Bold, fontFamily = amaranth,modifier = Modifier.align(Alignment.Center).padding(start = 15.dp))
      }
  }

}
