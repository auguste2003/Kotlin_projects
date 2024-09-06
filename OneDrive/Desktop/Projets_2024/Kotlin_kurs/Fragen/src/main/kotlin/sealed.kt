package org.example

// Wir verwenden sealed kLASSEN , wenn wir eine festgelegte Herachie von Klassen erstellen wollen .
sealed class Result{
    data class Success(val value:String): Result()
    data class Error(val exception: Exception): Result()
    object Loading: Result()

}
fun handleResult(result: Result){
    when(result){
        is Result.Success -> println("Succes : ${result.value}")
        is Result.Error -> println("Error : ${result.exception.message}")
        Result.Loading -> println("Loading")
    }
}
fun main() {
    val success = Result.Success("Data is loaded successfully")
    val error = Result.Error(Exception("An error occurred"))
    val loading = Result.Loading

    handleResult(loading)
    handleResult(error)
    handleResult(success)
}