package kotlin_way.basics.eingabe

fun main() {
    println("Bitte geben Sie Ihren Vornamen ein:")
    val vorname = readLine()

    println("Bitte geben Sie Ihren Nachnamen ein:")
    val nachname = readLine()

    println("Bitte geben Sie Ihr Alter ein:")
    val alter = readLine()?.toIntOrNull()

    if (vorname != null && nachname != null && alter != null) {
        println("Hallo, $vorname $nachname. Sie sind $alter Jahre alt.")
    } else {
        println("Es gab ein Problem mit Ihren Eingaben.")
    }
}
