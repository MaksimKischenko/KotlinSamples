package Classes

interface Roamable {
    var velocity: Int
        get() = 20
        set(value) {
            println("Unable to update velocity")
        }
}

class Mushroom(val size: Int, val isMagic: Boolean) {
    constructor(isMagic_param: Boolean) : this(0, isMagic_param) {
     //Код, который выполняется при вызове вторичного конструктора
    }
}

abstract class Animal(value: Int) {

    abstract fun makeNoise()
    abstract fun eat()

    open fun roam() {
        println("The Animal is roaming")
    }
    fun sleep() {
        println("The Animal is sleeping")
    }
}

class Hippo(name:Int) : Animal(name) {
    override fun makeNoise() {

    }

    override fun eat() {

    }
}
