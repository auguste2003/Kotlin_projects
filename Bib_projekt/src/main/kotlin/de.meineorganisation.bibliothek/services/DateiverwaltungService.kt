import java.io.File

object DateiverwaltungService {
    private val resourcesPath = "src/main/resources"  // Der Pfad zum Ressourcen-Ordner
    private val studentsFilePath = "$resourcesPath/students.json"
    private val dozentenFilePath = "$resourcesPath/dozenten.json"

    // Funktion zum Speichern der Studenten in die Datei
    fun saveStudents(students: List<Student>) {
        File(resourcesPath).mkdirs()  // Erstelle den Ordner, falls er nicht existiert
        JsonUtil.saveToJsonFile(students, studentsFilePath)
    }

    // Funktion zum Speichern der Dozenten in die Datei
    fun saveDozenten(dozenten: List<Dozent>) {
        File(resourcesPath).mkdirs()  // Erstelle den Ordner, falls er nicht existiert
        JsonUtil.saveToJsonFile(dozenten, dozentenFilePath)
    }

    // Funktion zum Laden der Studenten aus der Datei
    fun loadStudents(): List<Student> {
        return JsonUtil.loadFromJsonFile(studentsFilePath)
    }

    // Funktion zum Laden der Dozenten aus der Datei
    fun loadDozenten(): List<Dozent> {
        return JsonUtil.loadFromJsonFile(dozentenFilePath)
    }
}