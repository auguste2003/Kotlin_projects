package uqac.dim.tryhardstart.ui.screens.user.compte

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.amaranth
import uqac.dim.tryhardstart.ui.theme.arial
import uqac.dim.tryhardstart.viewmodel.BusinessAccountViewModel

// Sind wir in BebutzerModus aoder AgenceModus
@Composable
fun Compte(businessAccountViewModel: BusinessAccountViewModel){
    var expanded by remember { mutableStateOf(false) }
    // Par défaut, sélectionnez "Mode Agence"
    var selectedMode by businessAccountViewModel.selectedMode
    val modes = listOf("Reisebüro-Modus", "Benutzermodus")
    val modeselect by businessAccountViewModel.modeUser.collectAsState()
    Box(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth(0.56f)
        .background(Orange, RoundedCornerShape(topEnd = 5.dp, topStart = 5.dp))
        .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clickable(onClick = { expanded = true })
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card (
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 15.dp
                    ),
                    modifier = Modifier.size(30.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Green
                    )
                ){
                    Box (
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            tint = Color.White,
                            painter = painterResource(id = R.drawable.baseline_business_center_24),
                            contentDescription = null
                        )
                    }}
                Text(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = amaranth,
                    fontSize = 20.sp,
                    text = selectedMode,
                        //.fillMaxWidth(),
                    //textAlign = TextAlign.Center
                )
                Icon(
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.baseline_expand_more_24),
                    contentDescription = null)
            }
            
        }
        
        DropdownMenu(
            offset = DpOffset(x=0.dp,y= (-1).dp),
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Orange)
                .fillMaxWidth(0.515f)
                .height(50.dp)
        ) {
            modes.filter { it != selectedMode }.forEach { mode ->
                businessAccountViewModel.modeUser.value = mode != "Benutzermodus"
                DropdownMenuItem(
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)
                    ),
                    colors = MenuDefaults.itemColors(
                        textColor = Color.White

                    ),
                    text = {

                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .offset(y = (-8).dp),
                            text = mode,
                            fontFamily = amaranth,
                            textAlign = TextAlign.Center
                        )

                    },
                    onClick = {
                        selectedMode = mode
                        expanded = false
                    }
                )
            }
        }
    }
}