package uqac.dim.tryhardstart.viewmodel

import android.app.Activity
import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import uqac.dim.tryhardstart.model.UserRepository
import java.util.concurrent.TimeUnit

class SignupViewModel(): ViewModel(){
    val verificationID = MutableStateFlow("")
    private val _complete = MutableStateFlow(false)
     val completeVerify = mutableStateOf<Boolean>(false)
    val completeVerify1 = mutableStateOf<Boolean>(true)

    //val completeVerify = _completeVerify.asStateFlow()
    private val _showbottomNav = MutableStateFlow(false)
    val showbottomNav = _showbottomNav.asStateFlow()

    val complete = _complete.asStateFlow() // Exposition comme StateFlow pour l'immuabilité à l'extérieur du ViewModel
    var isLoading = MutableStateFlow(false)

    fun setComplete(value: Boolean) {
        _complete.value = value
    }
    fun setShowBottomNav(show:Boolean){
        _showbottomNav.value=show
    }

    private fun setCompleVerify(show: Boolean){
        completeVerify.value=show
    }
    var otp = mutableStateOf("")

    val message = mutableStateOf("")

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance();
    //Etats pour les champs de texte
    var username = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var phoneNumer = mutableStateOf("")
    var confirmPassword = mutableStateOf("")

    var signupStatusMessage = MutableStateFlow("")
    //Etats pour les messages d'erreur

    var usernameError = mutableStateOf<String?>(null)
    var emailError = mutableStateOf<String?>(null)
    var passwordError = mutableStateOf<String?>(null)
    var confirmPasswordError = mutableStateOf<String?>(null)
    var verifAuthe = mutableStateOf<Boolean>(false)

    fun validateUsername() {
        usernameError.value = if (username.value.length < 3) " Muss mindestens 3 Zeichen enthalten ." else null
    }

    fun validatePassword(){
        passwordError.value =
            if (password.value.length < 7)
                "Muss mindestens 07 Zeichen enthalten ."
            else if (!(password.value.contains('@')||password.value.contains('$')||password.value.contains('&')))
                "Muss mindestens en SOnderzeichen enthalten : @,$,&"
            else
                ""
    }
    fun confirmPassword(){
        confirmPasswordError.value =
            if (password.value != confirmPassword.value)
                "entspricht nicht "
            else
                ""
    }

    fun validateEmail(){
        emailError.value = if(!(Patterns.EMAIL_ADDRESS.matcher(email.value).matches()))
                   "Email is ungültig"

                else
                    ""
    }
   val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            isLoading.value=true
            // on below line updating message
            // and displaying toast message
            message.value = "Verifizierung erfolgreich"
            //Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            // on below line displaying error as toast message.
            isLoading.value=false
            message.value = "Fehler bei der Verifizierung des Benutzers:\n" + p0.message

            //Toast.makeText(context, "Verification failed..", Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken) {
            // this method is called when code is send
            super.onCodeSent(verificationId, p1)
            message.value = "gesendeteter Code : \n$verificationId"
            isLoading.value=false
            setComplete(true)

            verificationID.value = verificationId
        }
    }
     fun sendVerificationCode(
        number: String,
        activity: Activity,
    ) {
         isLoading.value=true
        // on below line generating options for verification code
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(number) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
     fun signInWithPhoneAuthCredential(
        activity: Activity
    ) {
         Log.d("bravo",verificationID.value)
         Log.d("bravo",otp.value)
         val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
             verificationID.value, otp.value.toString()
         )


        // on below line signing with credentials.
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                // displaying toast message when
                // verification is successful
                if (task.isSuccessful) {
                    setCompleVerify(true)
                    val user = hashMapOf(
                        "nom" to username.value,
                        "motDePasse" to password.value,
                        "Numero Telephone" to phoneNumer.value
                    )
                    val userId = mAuth.currentUser?.uid ?: return@addOnCompleteListener
                    if (!verifAuthe.value){
                        Firebase.firestore.collection("utilisateurs")
                            .document(userId)
                            .set(user)
                            .addOnSuccessListener {
                                isLoading.value = false
                                Log.d("Firestore", "DocumentSnapshot added")
                                signupStatusMessage.value = "Enregistrement réussi"
                                // Vous pouvez également gérer la navigation ou d'autres actions post-enregistrement ici
                            }
                            .addOnFailureListener { e ->
                                isLoading.value = false
                                Log.w("Firestore", "Error adding document", e)
                                signupStatusMessage.value = "Échec de l'enregistrement$e"
                            }
                        message.value = "Verifizierung erfolgreich"
                    }

                    //Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
                } else {
                    // Sign in failed, display a message
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code
                        // entered was invalid
                       // Toast.makeText(
                        message.value = "Fehler bei Verifizierung"
                           // context,
                            //"Verification failed.." + (task.exception as FirebaseAuthInvalidCredentialsException).message,
                            //Toast.LENGTH_SHORT
                        //).show()
                    }
                }
            }
    }
    fun verify(navController: NavController){
        viewModelScope.launch {
            if (completeVerify.value){
                navController.navigate("Startseite")
            }
        }
    }


    private fun allFields() = usernameError.value== null && emailError.value==null &&
                      passwordError.value== null && confirmPasswordError.value == null


    fun signUser() = viewModelScope.launch {
        if (allFields()) {
            signupStatusMessage.value = "Veuillez corriger les erreurs avant de soumettre."
            return@launch
        }

        signupStatusMessage.value = "Chargement..."
        try {
            val result = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.value, password.value).await()
            // Vérifiez ici si vous avez besoin de faire plus avec result, par exemple stocker des infos utilisateur supplémentaires dans Firestore
            signupStatusMessage.value = "Inscription réussie!"
        } catch (e: Exception) {
            signupStatusMessage.value = "Échec de l'inscription. Veuillez réessayer. ${e.message}"
        }
    }

    fun signOutUser(navController: NavController){
        setCompleVerify(false)
        setComplete(false)
        FirebaseAuth.getInstance().signOut()
        navController.navigate("Login") {
            popUpTo(0)
        }

    }

}