import java.io.File

object AusleiheService {
    private const val ausleihenFilePath = "src/main/resources/ausleihen.json" // Kann nie mehr geändert werden
    private const val AUSLEIH_GEBUEHR = 10.0 // Nur für primitive Datentypen Double , String und Int

    init {
        File(ausleihenFilePath).createNewFile()  // Datei erstellen, falls sie nicht existiert
    }

    // Funktion zur Identifikation eines Mitglieds
    fun findeMitglied(name: String, vorname: String, geburtsdatum: String): HochschulMitglied? {
        val studenten = DateiverwaltungService.loadStudents()
        val dozenten = DateiverwaltungService.loadDozenten()
        return studenten.find { it.name == name && it.vorname == vorname && it.geburtsdatum == geburtsdatum }
            ?: dozenten.find { it.name == name && it.vorname == vorname && it.geburtsdatum == geburtsdatum }
    }

    // Funktion zur Ausleihe eines Buches
    fun ausleiheBuch(mitglied: HochschulMitglied, buchTitel: String) {
        if (mitglied.kontostand >= AUSLEIH_GEBUEHR) {
            mitglied.kontostand -= AUSLEIH_GEBUEHR
            speichereAusleihe(mitglied, buchTitel)
            println("Das Buch '$buchTitel' wurde erfolgreich ausgeliehen. Neuer Kontostand: ${mitglied.kontostand}")
        } else {
            throw NichtGenugGuthabenException("Nicht genug Guthaben für die Ausleihe.")
        }
    }

    // Funktion zum Speichern der Ausleihe in einer Datei
    private fun speichereAusleihe(mitglied: HochschulMitglied, buchTitel: String) {
        val ausleihe = "${mitglied.vorname} ${mitglied.name} (${mitglied.rolle}) hat das Buch '$buchTitel' ausgeliehen."

        // Sicherstellen, dass die Datei existiert und falls leer, initialisieren
        val ausleihenListe = if (File(ausleihenFilePath).exists() && File(ausleihenFilePath).readText().isNotEmpty()) {
            JsonUtil.loadFromJsonFile<String>(ausleihenFilePath).toMutableList()
        } else {
            mutableListOf<String>()
        }

        ausleihenListe.add(ausleihe)
        JsonUtil.saveToJsonFile(ausleihenListe, ausleihenFilePath)
    }
}

    class NichtGenugGuthabenException(message: String) : Exception(message)