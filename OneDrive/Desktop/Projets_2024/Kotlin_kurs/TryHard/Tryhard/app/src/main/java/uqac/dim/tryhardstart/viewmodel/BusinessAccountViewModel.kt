package uqac.dim.tryhardstart.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BusinessAccountViewModel:ViewModel(){
//    init {
//        viewModelScope.launch {
//            verificationSuccess()
//        }
//    }
    var selectedMode = mutableStateOf("Beuntzermodus")
    var nomAgence = MutableStateFlow("")
    val adresseCourriel =MutableStateFlow("")
    val nombreDeBus = MutableStateFlow("")
    val adresseSiegeSocial = MutableStateFlow("")
    private val _compteBusinessVerify = MutableStateFlow(false)
    val compteBusinessVerify: StateFlow<Boolean> = _compteBusinessVerify
    fun setCompteBusinessVerify(value: Boolean) {
        _compteBusinessVerify.value = value
    }
    //Etats pour la gestion de L'UI
    val isLoading = MutableStateFlow(false)
    val isSuccessful = MutableStateFlow(false)
    val errorMessage = MutableStateFlow("")
    val modeUser = MutableStateFlow(true)
    private val db = FirebaseFirestore.getInstance()
    private val user = FirebaseAuth.getInstance().currentUser
    val userId = user?.uid

    fun enregistrementCompteBusiness(){
        isLoading.value = true
        val compteBusiness = hashMapOf(
            "nom_agence" to nomAgence.value,
            "adresse_courriel" to adresseCourriel.value,
            "nombre_de_bus" to nombreDeBus.value,
            "adresse_siege_social" to adresseSiegeSocial.value,
            "inscription_complete" to true
        )
        viewModelScope.launch {
            db.collection("comptes_business").document(userId.toString())
                .set(compteBusiness)
                .addOnSuccessListener {
                    // Enregistrement réussi
                    isLoading.value = false
                    isSuccessful.value = true
                }
                .addOnFailureListener { e ->
                    // Échec de l'enregistrement
                    isLoading.value = false
                    isSuccessful.value = false
                    errorMessage.value = e.localizedMessage ?: "Erreur inconnue"
                }
        }
    }

    fun verificationSuccess(){
            db.collection("comptes_business").document(userId.toString())
                .get()
                .addOnSuccessListener {
                        document ->
                    if (document!= null && document.exists()){
                        val businessComplete = document.getBoolean("inscription_complete")?:false
                        setCompteBusinessVerify(businessComplete)
                    }
                    else {
                        setCompteBusinessVerify(false)
                        // Le document n'existe pas, l'utilisateur doit s'inscrire
                    }
                }
                .addOnFailureListener { e ->
                    // Gérer l'erreur
                }


    }
}