import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object JsonUtil {
    val gson = Gson()

    // Funktion zum Speichern einer Liste in eine JSON-Datei
    fun <T> saveToJsonFile(data: List<T>, filePath: String) {
        val json = gson.toJson(data)
        File(filePath).writeText(json)
    }

    // Funktion zum Laden einer Liste aus einer JSON-Datei
    inline fun <reified T> loadFromJsonFile(filePath: String): List<T> {
        val json = File(filePath).readText()
        val type = object : TypeToken<List<T>>() {}.type
        return gson.fromJson(json, type)
    }
}