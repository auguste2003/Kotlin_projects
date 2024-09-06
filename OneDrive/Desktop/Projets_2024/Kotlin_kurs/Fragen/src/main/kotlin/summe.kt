package org.example



    fun main(){
 val list = listOf(1,2,3,4,5)
        println(summe( list))
    }

fun summe ( liste:List<Int>):Int{
    var sum:Int = 0 ;
    for (i in liste){
        if(i % 2 == 0){
            sum += i
        }
    }
    return sum
}
