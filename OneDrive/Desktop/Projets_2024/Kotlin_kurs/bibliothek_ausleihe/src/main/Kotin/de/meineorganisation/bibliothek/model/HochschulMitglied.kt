package main.Kotin.de.meineorganisation.bibliothek.model

open class HochschulMitglied(
    val id: String,               // Eindeutige ID (Matrikelnummer oder Dozentennummer)
    open val name: String,
    open val vorname: String,
    open val geburtsdatum: String,
   open  var kontostand: Double,       // Kontostand auf der Karte
    open val rolle: String             // "Student" oder "Dozent"
) {
    // Methode zur Ausgabe der Mitgliedsdaten
    open fun anzeigenDaten() {
        println("Name: $vorname $name, Geburtsdatum: $geburtsdatum, Kontostand: $kontostand, Rolle: $rolle")
    }
}