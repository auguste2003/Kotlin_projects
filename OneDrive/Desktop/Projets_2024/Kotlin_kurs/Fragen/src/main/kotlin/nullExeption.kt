package org.example

fun main(){
    var name:String? = "John"

    val length = name?.length ?: throw IllegalArgumentException("Name cannot be empty")

    println("Name length: $length")

    name = null

    try {
        val nullLength = name?.length ?: throw IllegalArgumentException("Name cannot be empty")

    }catch (e:IllegalArgumentException){
        println(e.message)
    }
}
