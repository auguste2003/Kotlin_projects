package org.example

// Wir erwertern die Klasse mit dieser Methode
fun String.isPalindrome(): Boolean {
    return this == this.reversed()
}
fun String.capitalizeWords(): String {
    return this.split(" ").joinToString(" ") { it.capitalize() }
}
fun main(){
    val word = "radar"
    println(word.isPalindrome())

    val sentence = "hello world from kotlin"
    println(sentence.capitalizeWords())
}