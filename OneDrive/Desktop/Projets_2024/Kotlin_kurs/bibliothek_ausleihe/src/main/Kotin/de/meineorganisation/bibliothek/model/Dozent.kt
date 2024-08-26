package main.Kotin.de.meineorganisation.bibliothek.model

data class Dozent(
    val dozentennummer: String,   // Dozentennummer als eindeutige ID für Dozenten
    val fakultät: String,         // Zusatzinfo: Fakultät
    val istStudent: Boolean,      // Gibt an, ob der Dozent auch Student ist
    override val   name: String,
    override val vorname: String,
    override val geburtsdatum: String,
    override var  kontostand: Double
) : HochschulMitglied(dozentennummer, name, vorname, geburtsdatum, kontostand, "Dozent") {

    override fun anzeigenDaten() {
        println("Dozent: $vorname $name, Fakultät: $fakultät, Dozentennummer: $dozentennummer, Ist auch Student: $istStudent")
    }
}