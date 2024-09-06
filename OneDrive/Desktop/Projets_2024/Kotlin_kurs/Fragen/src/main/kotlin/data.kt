package org.example

// Eine Data class ermöglicht die Benutzung von Funktionen toString() und copy()
data class User(val id: Int, val name: String)

fun main(args: Array<String>) {
    val user1 = User(1, "John")
    val user2 = User(1, "John")
    println(user1 == user2)
    println(user1)

    val user3 = user1.copy(name = "Bob")
    println(user3)
}