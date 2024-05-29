package Object

import org.example.Models.User

private val user = object : User(age = 25, name = "Max") {
    override fun printInfo() {
        println("object expressions: User")
    }
}

class ObjectExample {
    private fun getObject() = object {
        val x: String = "x"
    }

    fun printX() {
        println(getObject().x)
    }
}

val helloWorld = object {
    val hello = "Hello"
    val world = "World"
    // тип анонимных объектов - Any, поэтому `override` необходим в `toString()`
    override fun toString() = "$hello $world"
}

object Game {
    init {
        printInfo()
    }
    private fun printInfo() {
        println("Hello from class: $this")
    }

    private val user = object : User(age = 25, name = "Max") {
        override fun printInfo() {
            println("object : User()")
        }
    }
}