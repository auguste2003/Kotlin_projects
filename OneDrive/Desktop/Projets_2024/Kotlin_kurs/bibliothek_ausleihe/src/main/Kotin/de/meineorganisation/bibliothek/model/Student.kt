package main.Kotin.de.meineorganisation.bibliothek.model

data class Student(
    val matrikelnummer: String,   // Matrikelnummer als eindeutige ID für Studenten
    val studiengang: String,      // Zusatzinfo: Studiengang
   override var name: String,
    override var vorname: String,
    override var geburtsdatum: String,
    override var  kontostand: Double
) : HochschulMitglied(matrikelnummer, name, vorname, geburtsdatum, kontostand, "Student") {

    override fun anzeigenDaten() {
        println("Student: $vorname $name, Studiengang: $studiengang, Matrikelnummer: $matrikelnummer, Kontostand: $kontostand")
    }
}