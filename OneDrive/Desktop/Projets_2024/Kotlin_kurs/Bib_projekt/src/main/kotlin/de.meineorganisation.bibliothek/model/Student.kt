data class Student(
    val matrikelnummer: String,   // Matrikelnummer als eindeutige ID für Studenten
    val studiengang: String,      // Zusatzinfo: Studiengang
    // Eigenschaften werden aus HochschulMitglied übernommen, daher kein Override hier
) : HochschulMitglied(matrikelnummer, "", "", "", 0.0, "Student") {

    override fun anzeigenDaten() {
        println("Student: $vorname $name, Studiengang: $studiengang, Matrikelnummer: $matrikelnummer, Kontostand: $kontostand")
    }
}