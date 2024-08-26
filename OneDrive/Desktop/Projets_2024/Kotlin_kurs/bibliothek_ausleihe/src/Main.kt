import main.Kotin.de.meineorganisation.bibliothek.model.Dozent
import main.Kotin.de.meineorganisation.bibliothek.model.Student

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

fun main() {
    val student = Student(
        matrikelnummer = "123456",
        studiengang = "Informatik",
        name = "Müller",
        vorname = "Anna",
        geburtsdatum = "01.01.2000",
        kontostand = 50.0
    )

    val dozent = Dozent(
        dozentennummer = "D78910",
        fakultät = "Mathematik",
        istStudent = false,
        name = "Schneider",
        vorname = "Peter",
        geburtsdatum = "05.05.1970",
        kontostand = 100.0
    )

    student.anzeigenDaten()
    dozent.anzeigenDaten()
}