package uqac.dim.tryhardstart.model

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class UserRepository {
    suspend fun createUser(email: String, password: String): Boolean {
        return try {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
            true // Retourner true si la création est réussie
        } catch (e: Exception) {
            false // Retourner false si la création échoue
        }
    }
}
