package kotlin_way.basics.funktionen

fun main(args: Array<String>) {
println(add(3,5))
    println(dividieren(3,5))

}

fun add(a: Int, b: Int): Int {// Man muss immer den Ausgabe-typ angeben
    return a + b
}
fun dividieren(a: Int, b: Int) = a/b