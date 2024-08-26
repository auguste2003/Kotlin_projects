package kotlin_way.basics.loops

fun main(args: Array<String>) {
    val bereich =  (1..10)
    println(bereich )
    for (i in 100 downTo 90 step 2) { // Die Zahl verringert sich um 2 bei jeder Iteration
        println(i)
    }
    val zahlen = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(zahlen)
    val listOfList = listOf(1..20,100..115)

   loop1@ for (range in listOfList) {
        for (i in range) {
            if(i == 10 ){
                break@loop1 //  Beendet die äußere Schleife loop1@.
            }
        }
    }
}