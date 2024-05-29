package Models

interface Formatter {
    val yearMonthDate: String
}


class Car {

    companion object StdTimeExtension : Formatter {
        const val TAG = "TIME_EXTENSIONS"
        override val yearMonthDate = "yyyy-MM-d"
    }

    var brand: String = ""
    var model: String = ""
    var speed: Int = 0

    // Конструктор без аргументов
    constructor() {
        this.brand = "Unknown"
        this.model = "Unknown"
    }

    // Конструктор с аргументами
    constructor(brand: String, model: String, speed: Int) {
        this.brand = brand
        this.model = model
        this.speed = speed
    }

    fun displayInfo() {
        println("Машина: $brand $model")
        println("Скорость: $speed км/ч")
    }
}

