package uqac.dim.tryhardstart.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AdminViewModel :ViewModel(){
    var villeDepart = mutableStateOf("")
    var villeArrive = mutableStateOf("")
    var dateDepart = mutableStateOf("")
    var heureDepart = mutableStateOf("")
    var heureArrive = mutableStateOf("")
    var matricule = mutableStateOf("")
    var prix = mutableStateOf("")
    var promotion = mutableStateOf<Boolean>(false)
    var currentBusId = mutableStateOf("")
    var heureDepartSeconds = mutableStateOf(0)

    fun saveDataToFirestore() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val db = FirebaseFirestore.getInstance()

        val reservationData = hashMapOf(
            "idAgence" to userId,
            "villeDepart" to villeDepart.value,
            "villeArrive" to villeArrive.value,
            "dateDepart" to dateDepart.value,
            "heureDepart" to heureDepart.value,
            "heureDepartSeconds" to heureDepartSeconds.value,
            "heureArrive" to heureArrive.value,
            "matricule" to matricule.value,
            "prix" to prix.value,
            "promotion" to promotion.value
        )

        db.collection("reservations")
            .add(reservationData)
            .addOnSuccessListener {
                initPlace()
                initPlaceGauche()

            }
            .addOnFailureListener { e -> /* Gestion de l'erreur */ }
    }

    fun initPlace(){
        val db = FirebaseFirestore.getInstance()
        val numerosDePlaces = listOf("A3", "A4", "B3", "B4", "C3", "C4", "D3", "D4", "E3", "E4", "F3", "F4", "G3", "G4", "H3", "H4", "I3", "I4")
        val busRef = db.collection("bus").document(matricule.value)
        val seatsRef = busRef.collection("seats")

        numerosDePlaces.forEach { numeroChaise ->
            val seatData = hashMapOf(
                "numeroChaise" to numeroChaise,
                "reserved" to false,
                "nom" to ""
            )
            seatsRef.document(numeroChaise).set(seatData)
                .addOnSuccessListener {
                }
                .addOnFailureListener { e ->
                }
        }
    }


    fun initPlaceGauche(){
        val db = FirebaseFirestore.getInstance()
        val numerosDePlaces1 = listOf("A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2", "E1", "E2", "F1", "F2", "G1", "G2", "H1", "H2", "I1", "I2")
        val busRef1 = db.collection("bus").document(matricule.value)
        val seatsRef1 = busRef1.collection("seats1")

        numerosDePlaces1.forEach { numeroChaise ->
            val seatData = hashMapOf(
                "numeroChaise" to numeroChaise,
                "reserved" to false,
                "nom" to ""
            )
            seatsRef1.document(numeroChaise).set(seatData)
                .addOnSuccessListener {
                }
                .addOnFailureListener { e ->
                }
        }

    }


}