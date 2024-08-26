package kotlin_way.basics.variablen.typen

fun main(args:Array<String>) {
    val kommazahl = 2.5
    println(kommazahl)

    val kommazahl2: Float = 2.5f
    val grosseZahl = 199999999
    println(grosseZahl.javaClass.name)
    val t = "Beispiel "
    val mehrzeiligerText = """
        ^
        | pfeil 
        |
    """
    println(mehrzeiligerText)
    val text = "Zahl: ${grosseZahl +20 } Becher" // String Interploation
    println(text)

}