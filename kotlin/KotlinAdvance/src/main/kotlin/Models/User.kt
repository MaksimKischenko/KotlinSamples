package org.example.Models

open class User(var age: Int, var name: String) {
    fun showInfo() {
        println("Hash code: ${hashCode()}")
    }

    open fun printInfo() {
        println("Hello from simple class: $this")
    }
}

val users = listOf(
    User(name = "Mike", age = 29),
    User(name = "John", age = 31),
    User(name = "James", age = 27),
    User(name = "Vera", age = 29),
    User(name = "Lina", age = 31),
    User(name = "Hoard", age = 27),
)