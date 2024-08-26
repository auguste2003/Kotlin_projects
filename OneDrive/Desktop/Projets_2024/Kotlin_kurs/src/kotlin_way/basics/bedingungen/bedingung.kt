package kotlin_way.basics.bedingungen

fun main(args: Array<String>) {

    val alter = 65 ;
   val klassifizierung : String  =  if (alter >=63 ){
        println("Renteneintriit")
       "Renteneintriit"
    }else if (alter>=18 ){
        println("Volljährig")
        "Volljährig"
    }else{
        println("Jungendlicher/Kind")
        "Jungendlicher/Kind"
    }
    println(klassifizierung)

    val meinName = "Christopher "
    if (meinName == "Christopher ")
        println("willkommen Christopher ")

     /*when (meinName) {
        "Alfred " -> println("Howdy Alfred ")
        "Christopher " -> println("Howdy Alfred ")
        else -> println("Howdy Alfred ")
    }

      */

 val gross =    when (meinName) {
        "Alfred" -> "Howdy Alfred "
        "Christopher " -> "Howdy Alfred "
        is String -> ""
        else -> "Howdy Alfred "
    }


        val zahl = 5

        val typ = when {
            zahl < 0 -> "Negativ"
            zahl == 0 -> "Null"
            else -> "Positiv"
        }

        println("Die Zahl ist $typ.")


}















