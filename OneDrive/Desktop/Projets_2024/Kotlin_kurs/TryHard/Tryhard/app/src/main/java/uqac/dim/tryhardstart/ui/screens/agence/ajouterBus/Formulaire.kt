package uqac.dim.tryhardstart.ui.screens.agence.ajouterBus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.viewmodel.AdminViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulaire(adminViewModel: AdminViewModel){
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)

        ){
            Card(
                modifier = Modifier.size(50.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Green.copy(0.13f)
                )
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = painterResource(id = R.drawable.baseline_attach_money_24),
                        contentDescription = null,
                        tint = Green
                    )
                }

            }
            TextField(
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Green,
                    unfocusedContainerColor = Orange.copy(0.4f),
                    focusedIndicatorColor = Green,
                    focusedContainerColor = Orange.copy(0.4f)
                ),
                value = adminViewModel.prix.value,
                onValueChange ={
                      adminViewModel.prix.value =it
                },
                label = {
                    Text(text = "Price eingaben ")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
            )
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Checkbox(
                colors = CheckboxDefaults.colors(
                    checkedColor = Orange
                ),
                checked = adminViewModel.promotion.value, onCheckedChange = {
                    adminViewModel.promotion.value = !adminViewModel.promotion.value

                }
            )
            Text(text = "Einen Rabatt von 25% hinzufügen")

        }
       
       
    }
}