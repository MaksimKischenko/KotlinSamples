package org.example.Functions

import org.example.Models.User


typealias UserCallback = (user: User, id:Int) -> Unit
typealias UserErrorErrorCallback = (error: String) -> Unit

class ActionUser(
    private val userCallback: UserCallback,
) {

    fun makeCallBack(user: User) {
        var count = 0
        userCallback(
            user,
            count
        )
        count+=1
    }
}


// Определение интерфейса для функции обратного вызова
interface Callback {
    fun onResult(result: String)
}

// Функция, которая эмулирует выполнение длительной задачи в фоновом потоке
fun performTask(callback: Callback) {
    println("performTask: ${Thread.currentThread().name}")
    // Эмуляция задержки
    Thread.sleep(2000)

    // Получение результата задачи
    val result = "Task completed"

    // Вызов функции обратного вызова с результатом
    callback.onResult(result)
}

// Создание экземпляра интерфейса Callback и передача его в функцию performTask
val callback = object : Callback {
    override fun onResult(result: String) {
        println("callback: ${Thread.currentThread().name}")
        println("Результат задачи: $result")
    }
}