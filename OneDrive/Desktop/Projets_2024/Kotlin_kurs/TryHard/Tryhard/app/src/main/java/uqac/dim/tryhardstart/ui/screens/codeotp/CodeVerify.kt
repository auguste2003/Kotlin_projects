package uqac.dim.tryhardstart.ui.screens.codeotp

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.TextFieldDefaults

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.arial
import uqac.dim.tryhardstart.viewmodel.BusinessAccountViewModel
import uqac.dim.tryhardstart.viewmodel.SignupViewModel

// Verifizierung der OPT
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeVerify(navController: NavController, signupViewModel: SignupViewModel,businessAccountViewModel: BusinessAccountViewModel){
    val otpLength = 6
    val completeVerify = signupViewModel.completeVerify.value
    val context = LocalContext.current

    // Créer un tableau pour stocker chaque chiffre de l'OTP
    val otpDigits = remember { Array(otpLength) { mutableStateOf("") } }
    val focusManager = LocalFocusManager.current
   Column {
       Row(
           modifier = Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically,
       ) {
           // Créer un champ pour chaque chiffre de l'OTP
           for (i in otpDigits.indices) {
               OutlinedTextField(
                   value = otpDigits[i].value.toString(),
                   onValueChange = { newvalue ->
                       if (newvalue.length <= 1) {
                           otpDigits[i].value = newvalue
                           if (newvalue.isNotEmpty() && i < otpLength - 1) {
                               // Passer au champ suivant si ce n'est pas le dernier champ
                               focusManager.moveFocus(FocusDirection.Right)
                           }
                       }
                   },
                   textStyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold),
                   singleLine = true,
                   keyboardOptions = KeyboardOptions(
                       keyboardType = KeyboardType.Number,
                       imeAction = if (i == otpLength - 1) ImeAction.Done else ImeAction.Next
                   ),
                   keyboardActions = KeyboardActions(
                       onNext = { if (i < otpLength - 1) focusManager.moveFocus(FocusDirection.Right) },
                       onDone = { focusManager.clearFocus() }
                   ),
                   modifier = Modifier
                       .width(55.dp)
                       .height(65.dp) // Taille du champ, ajustez selon le besoin
                       .background(Color.White) // Couleur de fond de chaque champ
                       .padding(4.dp),


                   )
               if (i != otpDigits.size - 1) { // Pour ajouter de l'espace entre les champs sauf après le dernier
                   Spacer(Modifier.width(8.dp))
               }
           }
       }
       signupViewModel.otp.value=otpDigits.joinToString(separator = "") { it.value }



       Spacer(modifier = Modifier.height(40.dp))


       LaunchedEffect(completeVerify){
           if(completeVerify){
               businessAccountViewModel.verificationSuccess()
               navController.navigate("Startseite")

           }

       }
       Button(
           onClick = {
                     signupViewModel.signInWithPhoneAuthCredential(context as Activity)
                     //signupViewModel.verify(navController)

           },
           modifier = Modifier
               .fillMaxWidth()
               .height(60.dp)
               .padding(2.dp),
           colors = ButtonDefaults.buttonColors(
               containerColor = Orange,
               contentColor = Color.White
           ),
           shape = RoundedCornerShape(8.dp)
       ) {
           Text(
               text = "Veriefizieren",
               color = Color.White,
               fontWeight = FontWeight.Bold,
               fontFamily = arial,
               fontSize = 20.sp
           )
       }

   }


}