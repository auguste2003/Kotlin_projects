package kotlin_way.basics.variablen.typen

fun main(args: Array<String>) {

    /*
     toInt(): Konvertiert einen String in einen Int.
toDouble(): Konvertiert einen String in einen Double.
toFloat(): Konvertiert einen String in einen Float.
toLong(): Konvertiert einen String in einen Long.

     */
    val intValue = 123
    val doubleValue = intValue.toDouble() // Konvertiert Int zu Double (123.0)
    val floatValue = intValue.toFloat()   // Konvertiert Int zu Float (123.0)
    val longValue = intValue.toLong()     // Konvertiert Int zu Long (123)



    val number = 123
    val strValue = number.toString() // Konvertiert 123 zu String ("123")


}