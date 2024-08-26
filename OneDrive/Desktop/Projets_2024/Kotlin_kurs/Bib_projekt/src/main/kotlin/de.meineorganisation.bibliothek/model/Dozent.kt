data class Dozent(
    val dozentennummer: String,   // Dozentennummer als eindeutige ID für Dozenten
    val fakultät: String,         // Zusatzinfo: Fakultät
    val istStudent: Boolean       // Gibt an, ob der Dozent auch Student ist
    // Eigenschaften werden aus HochschulMitglied übernommen, daher kein Override hier
) : HochschulMitglied(dozentennummer, "", "", "", 0.0, "Dozent") {

    override fun anzeigenDaten() {
        println("Dozent: $vorname $name, Fakultät: $fakultät, Dozentennummer: $dozentennummer, Ist auch Student: $istStudent")
    }
}