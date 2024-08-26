package uqac.dim.tryhardstart.ui.screens.recherche

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.arial
import uqac.dim.tryhardstart.viewmodel.AdminViewModel
import uqac.dim.tryhardstart.viewmodel.BusinessAccountViewModel
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel


// Abfahrt
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun From(businessAccountViewModel: BusinessAccountViewModel,rechercheViewModel: RechercheViewModel,adminViewModel: AdminViewModel){
    val modeUser by businessAccountViewModel.modeUser.collectAsState()
    val options = listOf("Chicoutimi", "Montreal", "Toronto", "Douala", "Yaounde","Berlin", "München", "Hamburg", "Frankfurt", "Köln")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    val shape = if (expanded) RoundedCornerShape(8.dp).copy(bottomEnd = CornerSize(0.dp), bottomStart = CornerSize(0.dp))
    else RoundedCornerShape(8.dp)
    rechercheViewModel.setVilleDepart(selectedOptionText)
    adminViewModel.villeDepart.value=selectedOptionText
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(if (modeUser) 5.dp else 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)

            ) {
                Card(
                    modifier = Modifier.size(if(modeUser)60.dp else 50.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Orange.copy(0.13f)
                    )
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Icon(
                            modifier = Modifier
                                .size(30.dp),
                            painter = painterResource(id = R.drawable.outline_near_me_24),
                            contentDescription = null,
                            tint = Orange
                        )
                    }

                }
                Column(
                     //modifier = Modifier.padding(vertical = 10.dp)
                ) {
                    Text(text = "ABFAHRT", modifier = Modifier.padding(start = 10.dp), color = Orange)
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
                                // Ausgewählte Abfahrt übergeben
                                adminViewModel.villeDepart.value=selectedOptionText
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
                                unfocusedIndicatorColor = Color.White
                            )
                        )
                        ExposedDropdownMenu(
                            modifier = Modifier.zIndex(0f).background(Color.White),
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            options.forEach { selectionOption ->
                                DropdownMenuItem(
                                    colors = MenuDefaults.itemColors(

                                    ),
                                    modifier =
                                    Modifier
                                        .zIndex(0f)
                                        //.background(Color.White)

                                    ,
                                    text = { Text(
                                        selectionOption,

                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        ) },
                                    onClick = {
                                        selectedOptionText = selectionOption
                                        expanded = false
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}