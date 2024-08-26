package uqac.dim.tryhardstart.ui.screens.inscriptionPage

import android.app.Activity
import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material.TextFieldDefaults

import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
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
import uqac.dim.tryhardstart.viewmodel.SignupViewModel

// Formulaire in der Inscriptionsseite
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulaire(signupViewModel: SignupViewModel= viewModel(),navController: NavController){
    val complete by signupViewModel.complete.collectAsState()
    val context = LocalContext.current
    var phoneNumber: String by remember { mutableStateOf("") }
    var phone: String by remember { mutableStateOf("") }
    var fullPhoneNumber: String by remember { mutableStateOf("") }
    var isNumberValid: Boolean by remember { mutableStateOf(false) }
    val signupStatusMessage by signupViewModel.signupStatusMessage.collectAsState()
    var passwordVisibility by remember { mutableStateOf(false) }

    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 15.dp, start = 10.dp, end = 10.dp),
    ){
        Column {
            if (signupStatusMessage.isNotEmpty()) {
                Text(text = signupStatusMessage)
            }
            //Text(text = signupViewModel.message.value)
            Row(
                modifier = Modifier
                    .offset(y = 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text =signupViewModel.usernameError.value?:"",
                    color = Color.Red,
                    fontSize = 9.sp
                )
            }

            OutlinedTextField(
                leadingIcon = {
                              Icon(
                                  painter = painterResource(id = R.drawable.baseline_person_24) ,
                                  contentDescription = null)
                },
                label = {
                    Text(text = "Geben Sie Ihren Name ein ",fontFamily = arial)
                },
                modifier = Modifier
                    .fillMaxWidth()
                ,
                shape = RoundedCornerShape(23.dp),

                value =signupViewModel.username.value ,
                onValueChange ={
                               signupViewModel.username.value = it
                               signupViewModel.validateUsername()
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Green,
                    focusedBorderColor = Orange,
                    unfocusedBorderColor = Color.Black
                ),
                textStyle = TextStyle(
                    textDecoration = TextDecoration.None,
                    fontFamily = arial,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

            )
        }
        val focusManager = LocalFocusManager.current

        Column {

            val focusRequester = FocusRequester()
            if(!isNumberValid){
                if (phoneNumber.isNotEmpty()){
                    Row(
                        modifier = Modifier
                            .offset(y = (-3).dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                   // für ungültige Nummer
                        Text(
                            text ="Telefonnummer ungültig ",
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
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    //textColor = Green,
                    focusedLabelColor = Green,
                    focusedBorderColor = Orange,
                    unfocusedBorderColor = Color.Black
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
            signupViewModel.phoneNumer.value=fullPhoneNumber
//            if (phone.isNotEmpty()){
//                Text(text = phone)
//            }

            LaunchedEffect(Unit) {
                focusManager.clearFocus()
            }
        }

        Column {
            Row(
                modifier = Modifier
                    .offset(y = 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(
                    text =signupViewModel.passwordError.value?:"",
                    color = Color.Red,
                    fontFamily = arial,
                    fontSize = 10.sp
                )

            }

            OutlinedTextField(
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_lock_24) ,
                        contentDescription = null)
                },
               // isError = ,
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                                                      passwordVisibility=!passwordVisibility
                        },
                        painter = painterResource(
                            id = if
                                    (!passwordVisibility) R.drawable.baseline_visibility_off_24
                            else
                                   R.drawable.baseline_visibility_24
                        ) ,
                        contentDescription = null)
                },
                label = {
                    Text(text = "Geben Sie Ihr Passwort ein ",fontFamily = arial)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(23.dp),

                value =signupViewModel.password.value ,
                onValueChange ={
                               signupViewModel.password.value = it
                               signupViewModel.validatePassword()
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Green,
                    focusedBorderColor = Orange,
                    unfocusedBorderColor = Color.Black
                ),
                textStyle = TextStyle(
                    textDecoration = TextDecoration.None,
                    fontFamily = arial,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                visualTransformation =
                if(!passwordVisibility) PasswordVisualTransformation()
                else VisualTransformation.None
                ,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

            )
        }
        Column {
            Row(
                modifier = Modifier.offset(y=5.dp),
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = signupViewModel.confirmPasswordError.value?:"",
                    color = Color.Red,
                    fontSize = 11.sp
                    )
            }

            OutlinedTextField(
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_lock_24) ,
                        contentDescription = null)
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_visibility_off_24) ,
                        contentDescription = null)
                },
                label = {
                    Text(text = "Passwort bestätigen",fontFamily = arial)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(23.dp),

                value =signupViewModel.confirmPassword.value,
                onValueChange ={
                               signupViewModel.confirmPassword.value = it
                               signupViewModel.confirmPassword()
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Green,
                    focusedBorderColor = Orange,
                    unfocusedBorderColor = Color.Black
                ),
                textStyle = TextStyle(
                    textDecoration = TextDecoration.None,
                    fontFamily = arial,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

            )
        }

        LaunchedEffect(complete){
            if (complete){
               navController.navigate("codeOtp")
          }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(23.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green
            ),
            onClick = {

                signupViewModel.sendVerificationCode(fullPhoneNumber, context as Activity)
                phone = if (isNumberValid){
                    "Ungültige Telefonnummer"
                } else{
                    "Ungültige Telefonnummer "
                }
                if (signupViewModel.complete.value){
                    navController.navigate("codeOtp")
                }


            }
        ) {
            Text(text = "Registrieren", fontFamily = poppins)

        }
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
                    fontFamily = poppins
                )
            }}
        
    }
}