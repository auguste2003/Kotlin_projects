package uqac.dim.tryhardstart.viewmodel

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalTime

data class Trajet(
    val villeDepart: String,
    val villeArrive: String,
    val dateDepart: String,
    val heureDepart: String,
    val heureArrivee: String,
    val prix: String,
    val promotion: Boolean,
    val idBus:String,
)
data class Seat(
    var numeroChaise: String ="",
    var reserved: Boolean = false,
    var nom:String = ""

)
data class Seat1(
    var numeroChaise: String ="",
    var reserved: Boolean = false,
    var nom:String = ""
)

class RechercheViewModel():ViewModel(){
    var numeroChaise = mutableStateOf("")
    var numeroChaise1 = mutableStateOf("")
    val _trajets = MutableStateFlow<List<Trajet>>(emptyList())
    val trajets = _trajets.asStateFlow()
    private val _villeDepart = mutableStateOf("")
    private val _villeArrivee = mutableStateOf("")
    var dateDepart = mutableStateOf("")
    val textValidError = mutableStateOf("")
    var seats = mutableStateListOf<Seat>()
    var seats1 = mutableStateListOf<Seat1>()
    val villeDepart = _villeDepart
    val villeArrivee = _villeArrivee
    var currentBusId = mutableStateOf("")
    var bouttonPayer = mutableStateOf(false)
    var villeDepartTrajet = mutableStateOf("")
    var villeArriveTrajet = mutableStateOf("")
    var dateTrajet = mutableStateOf("")
    var heureDebutTrajet = mutableStateOf("")
    var nomReservateur = mutableStateOf("")
    var numeroTelephone = mutableStateOf("")
    var matriculeTrajet = mutableStateOf("")
    var heureFinTrajet = mutableStateOf("")
    var promotionTrajet = mutableStateOf<Boolean>(false)
    var prixTrajet = mutableStateOf("")
    private val user = FirebaseAuth.getInstance().currentUser
    val userId = user?.uid
    fun setVilleDepart(value:String){
        _villeDepart.value = value
    }

    fun setVilleArrivee(value: String){
        _villeArrivee.value = value
    }

    fun isAdresseValid(): Boolean {
        val isValid = _villeDepart.value != _villeArrivee.value
        textValidError.value = if (!isValid) "Les deux villes doivent être différentes." else ""
        return isValid
    }
    val currentTime = LocalTime.now().toSecondOfDay()

    fun rechercherTrajets() {
        val db = FirebaseFirestore.getInstance()
        Log.d("date",dateDepart.value)
        db.collection("reservations")
            .whereEqualTo("villeDepart", _villeDepart.value)
            .whereEqualTo("villeArrive", _villeArrivee.value)
            .whereGreaterThan("heureDepartSeconds",currentTime)
            .whereEqualTo("dateDepart", dateDepart.value)
            .orderBy("heureDepart")
            .get()
            .addOnSuccessListener { documents ->
                _trajets.value = documents.map { document ->
                    Trajet(
                        villeDepart = document.getString("villeDepart") ?: "",
                        villeArrive = document.getString("villeArrive") ?: "",
                        dateDepart = document.getString("dateDepart") ?: "",
                        heureDepart = document.getString("heureDepart") ?: "",
                        heureArrivee = document.getString("heureArrive") ?: "",
                        prix = document.getString("prix") ?: "",
                        promotion = document.getBoolean("promotion") ?: false,
                        idBus = document.getString("matricule")?:"",
                    )
                }
            }

    }

    //recuperation des sieges des l'initialisation du composable

    fun initSeat(){
        val db = FirebaseFirestore.getInstance()
        db.collection("bus").document(currentBusId.value).collection("seats").get()
            .addOnSuccessListener {
                documents->
                seats.clear()
                documents.forEach {
                    document->
                    val seat = document.toObject(Seat::class.java)
                    seats.add(seat)
                }
            }
    }
    fun initSeatGauche(){
        val db = FirebaseFirestore.getInstance()
        db.collection("bus").document(currentBusId.value).collection("seats1").get()
            .addOnSuccessListener {

                    documents->
                seats1.clear()

                documents.forEach {
                        document->
                    val seat = document.toObject(Seat1::class.java)
                    seats1.add(seat)
                }
            }

    }
     suspend fun tikectBook(){
        val db = FirebaseFirestore.getInstance()
        //val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        Log.d("user1",userId.toString())
        db.collection("utilisateurs").document(userId.toString()).get()
            .addOnSuccessListener {
                    document->
                val businessComplete = document.getString("motDePasse")?:false
                nomReservateur.value = document.getString("nom")?: "Auguste"
                numeroTelephone.value = document.getString("Numero Telephone")?:"+491771537507"
                val businessComplet = document.getString("nom")?:false
                Log.d("name",nomReservateur.value.toString())
                Log.d("busi",businessComplete.toString())
                Log.d("regardons","L'erreur")


            }
            .addOnFailureListener{
                Log.d("regardons","L'erreur")
            }

    }


    fun updateSeatStatus() {
        if (numeroChaise.value.isNotEmpty()){
            viewModelScope.launch {
                tikectBook()
            }
            val db = FirebaseFirestore.getInstance()
            val updates = hashMapOf<String, Any>(
                "nom" to nomReservateur,
                "reserved" to true
            )
            db.collection("bus").document(currentBusId.value)
                .collection("seats").document(numeroChaise.value)
                .update("reserved",true) // 'reserved' est maintenant un champ booléen
                .addOnSuccessListener {
                    // Mettre à jour l'interface utilisateur en fonction du nouvel état de la réservation
                }
                .addOnFailureListener { e ->
                    // Gérer l'erreur
                }
            db.collection("bus").document(currentBusId.value)
                .collection("seats").document(numeroChaise.value)
                .update("nom",nomReservateur.value)
                .addOnSuccessListener {
                    // Mettre à jour l'interface utilisateur en fonction du nouvel état de la réservation
                }
                .addOnFailureListener { e ->
                    // Gérer l'erreur
                }
        }

    }
    fun updateSeatStatusGauche() {
        viewModelScope.launch {
            tikectBook()

        }
        if (numeroChaise1.value.isNotEmpty()){
            Log.d("num1",numeroChaise1.value)
            Log.d("nom",nomReservateur.value)
            val db = FirebaseFirestore.getInstance()
            db.collection("bus").document(currentBusId.value)
                .collection("seats1").document(numeroChaise1.value)
                .update("reserved",true) // 'reserved' est maintenant un champ booléen
                .addOnSuccessListener {
                    // Mettre à jour l'interface utilisateur en fonction du nouvel état de la réservation
                }
                .addOnFailureListener { e ->
                    // Gérer l'erreur
                }
            db.collection("bus").document(currentBusId.value)
                .collection("seats1").document(numeroChaise1.value)
                .update("nom",nomReservateur.value)
                .addOnSuccessListener {
                    // Mettre à jour l'interface utilisateur en fonction du nouvel état de la réservation
                }
                .addOnFailureListener { e ->
                    // Gérer l'erreur
                }

        }

    }
    private var mediaPlayer: MediaPlayer? = null

    fun playSound(context: Context, soundResId: Int) {
        // Release any previous player
        mediaPlayer?.release()

        // Create a new MediaPlayer for the context and specific sound resource
        mediaPlayer = MediaPlayer.create(context, soundResId).apply {
            setOnCompletionListener { mp ->
                mp.release()
            }
            start()
        }
    }

    override fun onCleared() {
        // Make sure to release the MediaPlayer when the ViewModel is cleared
        mediaPlayer?.release()
        mediaPlayer = null
    }














}