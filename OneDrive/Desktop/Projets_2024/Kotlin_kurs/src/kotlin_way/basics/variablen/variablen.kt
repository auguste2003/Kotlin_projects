
package kotlin_way.basics.variablen

fun main(args: Array<String>) {

    /**
     * var für Variablen und val für Konstanten
     */
    var zahl = 1

    var ergebnis = zahl +2
    println(ergebnis)
    println(ergebnis.javaClass.name)

    val text : String = "Hallo"
    println(text+"World")
}