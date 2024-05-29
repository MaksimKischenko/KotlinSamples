package ScopeFunc

import Models.Car
import java.io.File

fun scopeFunctions() {
    //TODO apply
    val car = Car().apply {
        brand = "Ferrari"
        model = "F510"
        speed = 310
    }
    //Также можно сказать, что функции неявно вызываются для получателя.
    val guestList: List<String> = mutableListOf<String>().apply {
        add("NIKE")
    }.toList()

    //TODO let
    var carWithLet: Car? = null
    carWithLet = Car()
    //Большинство программистов Kotlin, стремящихся обеспечить null-безопасность, выбирают let,
    val resultFromLet = carWithLet.let {
        it.brand = "Ferrari"
        it.model = "F510"
        it.speed = 310
        "${it.brand} + ${it.model} + ${it.speed}"
    }

    //TODO run
    //возвращает результат лямбда-выражения вместо имени самого получателя, как let.
    val resultFromRun = Car().run {
        brand = "Ferrari"
        model = "F510"
        speed = 310
        "$brand + $model + $speed"
    }

    val healthPoints = 90
    val healthStatus = run {
        if (healthPoints == 100) "perfect health" else "has injuries"
    }

    //TODO with

    // В отличие от функций обратного вызова, о которых мы рассказали ранее,
    // with требует, чтобы аргумент передавался в первом параметре
    val numbers = mutableListOf(1, 2, 3)
    with(numbers) {
        add(4)
        remove(1)
        println(this)
    }

    val nameTooLong = with("Polarcubis, Supreme Master of NyetHack") {
        length >= 20
    }

    //TODO also

    //Из-за этого функция also особенно удобна для добавления различных побочных
    // эффектов от общего источника.
    var fileContents: List<String>
    File("file.txt").also { print(it.name) }.readLines().also { fileContents = it }


    //TODO takeIf

    //Последняя функция области видимости, которую мы рассмотрим, — takeIf —
    // немного отличается от других: она вычисляет условие, или предикат,
    // заданное лямбда-выражением, которое возвращает истинное или ложное значение.
    val fileContents2 = File("myfile.txt").takeIf { it.exists() }?.readText()

    //Вариант с takeIf не требует временной переменной file и явного возврата null.
// takeIf удобно использовать для проверки условия перед присваиванием значения
// переменной или продолжением работы. Концептуально функция takeIf напоми- нает оператор if,
// но у нее есть преимущество прямого вызова для экземпляра, что часто позволяет
// избавиться от временной переменной.
}
