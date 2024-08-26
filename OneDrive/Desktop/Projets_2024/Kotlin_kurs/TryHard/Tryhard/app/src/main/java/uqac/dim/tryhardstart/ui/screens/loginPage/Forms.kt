package uqac.dim.tryhardstart.ui.screens.loginPage

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.togitech.ccp.component.TogiCountryCodePicker
import uqac.dim.tryhardstart.R
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.NoirClair
import uqac.dim.tryhardstart.ui.theme.Orange
import uqac.dim.tryhardstart.ui.theme.Swipe
import uqac.dim.tryhardstart.ui.theme.amaranth
import uqac.dim.tryhardstart.ui.theme.arial
import uqac.dim.tryhardstart.ui.theme.poppins
import uqac.dim.tryhardstart.ui.theme.roboto
import uqac.dim.tryhardstart.viewmodel.RechercheViewModel
import uqac.dim.tryhardstart.viewmodel.SignupViewModel

// Formular zur Anmeldung
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Forms(navController: NavController,signupViewModel: SignupViewModel,rechercheViewModel: RechercheViewModel){
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()
    var phoneNumber: String by remember { mutableStateOf("") }
    var phone: String by remember { mutableStateOf("") }
    var fullPhoneNumber: String by remember { mutableStateOf("") }
    var isNumberValid: Boolean by remember { mutableStateOf(false) }
    val complete by signupViewModel.complete.collectAsState()

    val inscription = buildAnnotatedString {
        withStyle(style = SpanStyle(color = NoirClair)) {
            append("Haben Sie kein Konto?")
        }
        withStyle(
            style = SpanStyle(
                color = Orange,
                fontFamily = arial,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 27.sp
            ),
        ) {
            append("Registrieren")

        }
    }
    Column(
        modifier = Modifier.padding(13.dp)
    ) {
        Text(
            text = "Anmelden",
            fontFamily = arial,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        if(!isNumberValid){
            if (phoneNumber.isNotEmpty()){
                Row(
                    modifier = Modifier
                        .offset(y = (-3).dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Text(
                        text ="Ungültige Telfonnummer",
                        color = Color.Red,
                        fontFamily = arial,
                        fontSize = 10.sp
                    )

                }
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        TogiCountryCodePicker(
            showError = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp)
                .focusRequester(focusRequester),
            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                //textColor = Green,
                focusedLabelColor = Green,
                focusedBorderColor = Orange,
                unfocusedBorderColor = Color.Black,

            ) ,
            textStyle = TextStyle(
                textDecoration = TextDecoration.None,
                fontFamily = arial,
                fontSize = 20.sp,
                // fontWeight = FontWeight.Bold
            ),
            //label = { Text("Phone Number") },
            onValueChange = { (code, phone), isValid ->
                //Log.d("CCP", "onValueChange: $code $phone -> $isValid")
                phoneNumber = phone
                fullPhoneNumber = code + phone
                isNumberValid = isValid
            },


            )
        LaunchedEffect(Unit) {
            focusManager.clearFocus()
        }
        LaunchedEffect(complete){
            if (complete){
                rechercheViewModel.playSound(context,R.raw.notification_connexion)
                navController.navigate("codeOtp")
            }
        }

        signupViewModel.phoneNumer.value=fullPhoneNumber
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            leadingIcon = {
//                          Icon(
//                              painter = painterResource(id = R.drawable.baseline_email_24) ,
//                              contentDescription = null)
//            },
//
//            colors = OutlinedTextFieldDefaults.colors(
//                unfocusedContainerColor = Color(0xFFC6D2EE),
//                unfocusedBorderColor = Color(0xFFEDF0F7),
//            ),
//            value = "",
//            onValueChange = {
//
//            },
//            label = {
//                Text(text = "Email", fontWeight = FontWeight.Bold)
//            }
//            )
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            leadingIcon = {
//                Icon(
//                    painter = painterResource(id = R.drawable.baseline_lock_24),
//                    contentDescription = null)
//            },
//            trailingIcon = {
//                           Icon(
//                               painter = painterResource(id = R.drawable.baseline_visibility_off_24),
//                               contentDescription =null )
//            },
//            colors = OutlinedTextFieldDefaults.colors(
//                unfocusedContainerColor = Color(0xFFC6D2EE),
//                unfocusedBorderColor = Color(0xFFEDF0F7),
//            ),
//            value = "",
//            onValueChange = {
//
//            },
//            label = {
//                Text(text = "Mot de passe",fontWeight = FontWeight.Bold)
//            }
 //       )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green,
                contentColor = Color.White,
            ),
            shape = RoundedCornerShape(5.dp),
            onClick = {
                signupViewModel.verifAuthe.value=true
                signupViewModel.sendVerificationCode(fullPhoneNumber, context as Activity)
                phone = if (isNumberValid){
                    " Telefonnummer gültig"
                } else{
                    " Telefonnummer ungültig"
                }
                if (signupViewModel.complete.value){
                    navController.navigate("codeOtp")
                }
            }
        ) {
            Text(
                text = "Anmelden",
                fontWeight = FontWeight.Bold,
                fontFamily = arial,
                fontSize = 20.sp
            )
            
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Passwort vergessen ?",
            color = Orange,
            fontFamily = arial,
            textAlign = TextAlign.End
        )
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
        ){

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(2.dp)
                    .background(Color.LightGray)
            )
            Text(text = "ORDER", color = NoirClair, modifier = Modifier.padding(start = 2.dp, end = 2.dp), fontWeight = FontWeight.ExtraBold)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.LightGray)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .height(50.dp)
                ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange.copy(0.1f),
                    contentColor = Color.Black,
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.Black),
                onClick = { /*TODO*/ }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .size(27.dp),
                        painter = painterResource(id = R.drawable.googl) ,
                        contentDescription =null )
                    Text(
                        modifier = Modifier,
                        text = "Mit Google anmelden",
                        fontFamily = arial
                    )
                }


            }
            Button( modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange.copy(0.1f),
                    contentColor = Color.Black,
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.Black),
                onClick = { /*TODO*/ }){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .size(27.dp)
                        ,
                        painter = painterResource(id = R.drawable.facebook_logo) ,
                        contentDescription = null)
                    Text(
                       // modifier = Modifier.fillMaxWidth(),
                        text = "Mit Facebook anmelden",
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontFamily = arial
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(25.dp))

        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clickable {
                navController.navigate("Inscription")
            }, textAlign = TextAlign.Center, text = inscription)
    }

}