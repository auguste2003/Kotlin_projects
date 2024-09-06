package org.example

fun performOperation(x:Int , operation:(Int)->Int):Int {
    return operation(x)
}
// generische Funktion
fun <T> swap(a:T, b:T):Pair<T,T>{
    return Pair(b,a)
}
// generische Klasse
class Box<T>(val value: T)


fun main(args: Array<String>) {
    val square = performOperation(5){it*it}
    println(square)

    // Lambdas
    val numbers = listOf(1,2,3,4,5)
    val doubled = numbers.map { it * it }
    val evenNumber = numbers.filter { it % 2 == 0}
    val sum = numbers.reduce{acc,number -> acc + number}
    val flatMapped = numbers.flatMap{ listOf(listOf(it, it*2))}
    println(flatMapped)
    println(sum)
    println(evenNumber)
    println(doubled)

    val intBox = Box(1)
    val StingBox = Box("Hello")
    println(intBox.value)
    println(StingBox.value)

    val result = swap(StingBox.value, intBox.value)
    val result2 = swap(2,5)
    println(result2)
    println(result)
}