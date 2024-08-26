import java.util.*

fun main() {
  /*  // Liste von 20 Studenten
    val students = listOf(
        Student("123001", "Informatik").apply {
            name = "Müller"; vorname = "Anna"; geburtsdatum = "01.01.2000"; kontostand = 50.0
        },
        Student("123002", "Maschinenbau").apply {
            name = "Schmidt"; vorname = "Lukas"; geburtsdatum = "15.05.1998"; kontostand = 30.0
        },
        Student("123003", "Elektrotechnik").apply {
            name = "Weber"; vorname = "Laura"; geburtsdatum = "12.07.2000"; kontostand = 40.0
        },
        Student("123004", "Wirtschaftsinformatik").apply {
            name = "Fischer"; vorname = "Tim"; geburtsdatum = "22.08.1999"; kontostand = 70.0
        },
        Student("123005", "Medizin").apply {
            name = "Schneider"; vorname = "Jasmin"; geburtsdatum = "09.04.1998"; kontostand = 60.0
        },
        Student("123006", "Bauingenieurwesen").apply {
            name = "Wagner"; vorname = "Simon"; geburtsdatum = "19.11.2001"; kontostand = 20.0
        },
        Student("123007", "Architektur").apply {
            name = "Becker"; vorname = "Emily"; geburtsdatum = "03.05.2000"; kontostand = 45.0
        },
        Student("123008", "Psychologie").apply {
            name = "Hoffmann"; vorname = "Jan"; geburtsdatum = "27.09.1999"; kontostand = 55.0
        },
        Student("123009", "Biologie").apply {
            name = "Schäfer"; vorname = "Laura"; geburtsdatum = "14.02.1997"; kontostand = 35.0
        },
        Student("123010", "Chemie").apply {
            name = "Koch"; vorname = "David"; geburtsdatum = "08.06.1998"; kontostand = 50.0
        },
        Student("123011", "Physik").apply {
            name = "Bauer"; vorname = "Lena"; geburtsdatum = "11.12.2000"; kontostand = 40.0
        },
        Student("123012", "Mathematik").apply {
            name = "Richter"; vorname = "Sophia"; geburtsdatum = "08.08.2001"; kontostand = 60.0
        },
        Student("123013", "Informatik").apply {
            name = "Klein"; vorname = "Max"; geburtsdatum = "17.01.2002"; kontostand = 70.0
        },
        Student("123014", "Maschinenbau").apply {
            name = "Wolf"; vorname = "Mia"; geburtsdatum = "26.03.1999"; kontostand = 25.0
        },
        Student("123015", "Elektrotechnik").apply {
            name = "Neumann"; vorname = "Tom"; geburtsdatum = "04.07.2000"; kontostand = 45.0
        },
        Student("123016", "Wirtschaftsinformatik").apply {
            name = "Schwarz"; vorname = "Lisa"; geburtsdatum = "21.11.1998"; kontostand = 35.0
        },
        Student("123017", "Medizin").apply {
            name = "Zimmermann"; vorname = "Paul"; geburtsdatum = "30.06.2001"; kontostand = 50.0
        },
        Student("123018", "Bauingenieurwesen").apply {
            name = "Braun"; vorname = "Lea"; geburtsdatum = "12.05.1997"; kontostand = 40.0
        },
        Student("123019", "Architektur").apply {
            name = "Krüger"; vorname = "Ben"; geburtsdatum = "14.02.1998"; kontostand = 55.0
        },
        Student("123020", "Mathematik").apply {
            name = "Hartmann"; vorname = "Clara"; geburtsdatum = "09.08.2001"; kontostand = 60.0
        }
    )

    // Liste von 10 Dozenten
    val dozenten = listOf(
        Dozent("D001", "Mathematik", false).apply {
            name = "Schneider"; vorname = "Peter"; geburtsdatum = "05.05.1970"; kontostand = 100.0
        },
        Dozent("D002", "Informatik", true).apply {
            name = "Fischer"; vorname = "Maria"; geburtsdatum = "22.03.1980"; kontostand = 150.0
        },
        Dozent("D003", "Physik", false).apply {
            name = "Wagner"; vorname = "Klaus"; geburtsdatum = "10.10.1965"; kontostand = 120.0
        },
        Dozent("D004", "Chemie", false).apply {
            name = "Meyer"; vorname = "Susanne"; geburtsdatum = "15.01.1975"; kontostand = 140.0
        },
        Dozent("D005", "Biologie", true).apply {
            name = "Schulz"; vorname = "Rainer"; geburtsdatum = "29.09.1968"; kontostand = 160.0
        },
        Dozent("D006", "Wirtschaftsinformatik", false).apply {
            name = "Hoffmann"; vorname = "Christine"; geburtsdatum = "08.11.1982"; kontostand = 130.0
        },
        Dozent("D007", "Medizin", false).apply {
            name = "Schäfer"; vorname = "Uwe"; geburtsdatum = "03.07.1960"; kontostand = 110.0
        },
        Dozent("D008", "Architektur", true).apply {
            name = "Koch"; vorname = "Brigitte"; geburtsdatum = "21.12.1978"; kontostand = 170.0
        },
        Dozent("D009", "Bauingenieurwesen", false).apply {
            name = "Richter"; vorname = "Helmut"; geburtsdatum = "17.04.1955"; kontostand = 150.0
        },
        Dozent("D010", "Biologie", true).apply {
            name = "Becker"; vorname = "Ingrid"; geburtsdatum = "03.03.1975"; kontostand = 110.0
        }
    )

    // Daten in JSON-Dateien speichern
    DateiverwaltungService.saveStudents(students)
    DateiverwaltungService.saveDozenten(dozenten)

    // Daten aus JSON-Dateien laden
    val loadedStudents = DateiverwaltungService.loadStudents()
    val loadedDozenten = DateiverwaltungService.loadDozenten()

    // Geladene Daten anzeigen
    println("Geladene Studenten:")
    loadedStudents.forEach { it.anzeigenDaten() }

    println("\nGeladene Dozenten:")
    loadedDozenten.forEach { it.anzeigenDaten() }

   */


        val scanner = Scanner(System.`in`)

        println("Willkommen im Bibliothekssystem!")

        // Schritt 1: Mitglied identifizieren
        print("Bitte geben Sie Ihren Namen ein: ")
        val name = scanner.nextLine()

        print("Bitte geben Sie Ihren Vornamen ein: ")
        val vorname = scanner.nextLine()

        print("Bitte geben Sie Ihr Geburtsdatum (TT.MM.JJJJ) ein: ")
        val geburtsdatum = scanner.nextLine()

        val mitglied = AusleiheService.findeMitglied(name, vorname, geburtsdatum)

        if (mitglied != null) {
            println("Hallo, ${mitglied.vorname} ${mitglied.name}. Ihr aktueller Kontostand beträgt ${mitglied.kontostand}€.")

            // Schritt 2: Ausleihe entscheiden
            print("Möchten Sie ein Buch ausleihen? (ja/nein): ")
            val ausleiheAntwort = scanner.nextLine()

            if (ausleiheAntwort.equals("ja", ignoreCase = true)) {
                if (mitglied.kontostand >= 10.0) {
                    // Benutzer nach dem Buchtitel fragen
                    print("Bitte geben Sie den Titel des Buches ein, das Sie ausleihen möchten: ")
                    val buchTitel = scanner.nextLine()
                    AusleiheService.ausleiheBuch(mitglied, buchTitel)
                } else {
                    println("Ihr Guthaben reicht nicht aus, um ein Buch auszuleihen.")

                    // Schritt 3: Guthaben aufladen
                    print("Möchten Sie Guthaben aufladen? (ja/nein): ")
                    val guthabenAntwort = scanner.nextLine()

                    if (guthabenAntwort.equals("ja", ignoreCase = true)) {
                        print("Bitte geben Sie den Betrag ein, den Sie aufladen möchten: ")
                        val aufladeBetrag = scanner.nextDouble()
                        mitglied.kontostand += aufladeBetrag
                        println("Ihr neuer Kontostand beträgt ${mitglied.kontostand}€.")

                        // Erneute Ausleihe nach Aufladen des Guthabens
                        if (mitglied.kontostand >= 10.0) {
                            scanner.nextLine()  // Clear buffer
                            print("Bitte geben Sie den Titel des Buches ein, das Sie ausleihen möchten: ")
                            val buchTitel = scanner.nextLine()
                            AusleiheService.ausleiheBuch(mitglied, buchTitel)
                        } else {
                            println("Ihr Guthaben reicht immer noch nicht aus. Auf Wiedersehen!")
                        }
                    } else {
                        println("Vielen Dank für Ihren Besuch. Auf Wiedersehen!")
                    }
                }
            } else {
                println("Vielen Dank für Ihren Besuch. Auf Wiedersehen!")
            }

            // Speichern des aktualisierten Mitglieds (wenn es ein Student ist)
            if (mitglied is Student) {
                val studenten = DateiverwaltungService.loadStudents().toMutableList()
                studenten.removeIf { it.matrikelnummer == mitglied.id }
                studenten.add(mitglied)
                DateiverwaltungService.saveStudents(studenten)
            }

            // Speichern des aktualisierten Mitglieds (wenn es ein Dozent ist)
            if (mitglied is Dozent) {
                val dozenten = DateiverwaltungService.loadDozenten().toMutableList()
                dozenten.removeIf { it.dozentennummer == mitglied.id }
                dozenten.add(mitglied)
                DateiverwaltungService.saveDozenten(dozenten)
            }
        } else {
            println("Mitglied nicht gefunden. Bitte überprüfen Sie Ihre Eingaben.")
        }

        println("Programm beendet.")
    }