package uqac.dim.tryhardstart.ui.screens.agence.ajouterBus

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import my.nanihadesuka.compose.LazyColumnScrollbar
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.arial
import uqac.dim.tryhardstart.ui.theme.poppins
import uqac.dim.tryhardstart.viewmodel.AdminViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeureDepart(adminViewModel: AdminViewModel){
    val options = List(48) { index ->
        val hour = index / 2 + 1
        val minute = if (index % 2 == 0) "00" else "30"
        "${hour.toString().padStart(2, '0')}H:$minute"
    }
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    adminViewModel.heureDepart.value = selectedOptionText
    val shape = if (expanded) RoundedCornerShape(8.dp).copy(bottomEnd = CornerSize(0.dp), bottomStart = CornerSize(0.dp))
    else RoundedCornerShape(8.dp)
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Card(
            modifier = Modifier.size(50.dp),
            colors = CardDefaults.cardColors(
                containerColor = Orange.copy(0.13f)
            )
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_access_time_24),
                    contentDescription =null ,
                    tint = Orange
                )
            }

        }

        ExposedDropdownMenuBox(
            modifier = Modifier.background(Color.White),
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {

            TextField(
                modifier = Modifier.menuAnchor(),
                textStyle = TextStyle.Default.copy(
                    fontSize = 18.sp,
                    fontFamily = arial,
                    fontWeight=  FontWeight.Bold),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {
                    adminViewModel.heureDepart.value= selectedOptionText

                },
                //label = { Text("DEPART", fontSize = 16.sp, fontWeight = FontWeight.Bold, ) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                shape = shape,
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Orange,
                    unfocusedIndicatorColor = Color.LightGray
                )
            )
        }
        if (expanded) {
            Dialog(onDismissRequest = { expanded = false }) {
                val listData = (0..10).toList()
                val listState = rememberLazyListState()
                Card(
                    modifier = Modifier.offset(x= (-5).dp,y= (-20).dp),
                    shape = RoundedCornerShape(4.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    LazyColumnScrollbar(
                        listState,
                        alwaysShowScrollBar = true,
                        modifier = Modifier
                            .height(150.dp)
                            .width(170.dp)
                            .background(Color.White),
                        thumbMinHeight = 0.3f,
                        thickness = 3.dp
                    ) {
                        LazyColumn(state = listState, modifier = Modifier.height(150.dp)) {
                            items(options) {
                                Text(
                                    text = " $it",
                                    //textAlign = TextAlign.Center,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = arial,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(11.dp)
                                        .clickable {
                                            selectedOptionText = it
                                            val parts = selectedOptionText.split("H:")

                                            if (parts.size == 2) {
                                                val hours = parts[0].toIntOrNull() ?: 0
                                                val minutes = parts[1].toIntOrNull() ?: 0
                                                // Conversion en secondes
                                                val totalSeconds = hours * 3600 + minutes * 60
                                                Log.d("second",totalSeconds.toString())
                                                // Mise à jour de la variable dans le ViewModel
                                                adminViewModel.heureDepartSeconds.value = totalSeconds
                                            }
                                            expanded=false
                                        }


                                )
                            }
                        }
                    }
                }
            }

        }
    }
}