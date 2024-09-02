
object ExceptionHandling {

    // Methode zur Behandlung von spezifischen Ausnahmen
    fun handleException(exception: Exception) {
        when (exception) {
            is NichtGenugGuthabenException -> {
                println("Fehler: ${exception.message}. Bitte laden Sie Guthaben auf.")
                logException(exception)
            }
            else -> {
                println("Ein unerwarteter Fehler ist aufgetreten: ${exception.message}")
                logException(exception)
            }
        }
    }

    // Methode zum Protokollieren von Ausnahmen (hier nur als Konsolenausgabe)
    private fun logException(exception: Exception) {
        // In einer realen Anwendung würdest du hier in eine Datei schreiben oder ein Logging-Framework verwenden
        println("Protokolliere Ausnahme: ${exception.javaClass.simpleName} - ${exception.message}")
    }
}