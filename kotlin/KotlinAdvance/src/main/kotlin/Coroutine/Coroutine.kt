package org.example.Coroutineimport kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.net.URL

class GpxRecordingService {

    companion object {
        var isStarted: Boolean = false
            private set(value) {
                println("set $value")
                field = value
            }
        fun startRecording() {
            isStarted = true
            println("Recording started")
        }
    }
}
//private suspend fun main(){

//    simpleDelay()
//    coroutineDelay()
//    severalCoroutineDelay()
//    runBlockingDelay()
//    nestedCoroutineDelay()

//    withoutJoin()
//    withJoin()
//    delayCoroutine()

//    asyncCoroutine()
//    asyncCoroutineWithResult()
//    severalAsyncWithResult()
//    delayAsyncWithResult()


//    withoutDispatcher()
//    withUnconfinedDispatcher()
//    withNamedDispatcher()

//    canselCoroutine()
//    canselCoroutineWithException()
//    canselAsyncCoroutineWithException()

//    channelSetGet()
//    channelSetGetForMore()
//}


suspend fun main() {
    println(GpxRecordingService.startRecording())
//    performNonBlockingOperation()
}

suspend fun performBlockingOperation(): String {
    // Симуляция блокирующей операции, например, загрузка данных из сети
    delay(2000) // Задержка на 2 секунды
    return "Результат блокирующей операции"
}

suspend fun performNonBlockingOperation() {
    println("Начало неблокирующей операции")

    val result = withContext(Dispatchers.IO) {
        performBlockingOperation()
    }

    println("Результат: $result")

    println("Конец неблокирующей операции")
}


//@OptIn(ExperimentalCoroutinesApi::class)
//fun CoroutineScope.getUsers(): ReceiveChannel<String> = produce{
//    val users = listOf("Tom", "Bob", "Sam")
//    for (user in users) {
//        send(user)
//    }
//}

private suspend fun simpleDelay400(): String {
    for (i in 0..5) {
        delay(400L)
        println("simpleDelay400 $i")
    }
    return "END simpleDelay400"
}

private suspend fun simpleDelay200(): String {
    for (i in 0..5) {
        delay(200L)
        println("simpleDelay200 $i")
    }
    return "END simpleDelay200"
}


private suspend fun coroutineDelay() = coroutineScope {
    launch {
        for (i in 0..5) {
            delay(400L)
            println(i)
        }
    }
    println("Hello Coroutines")
}

private suspend fun severalCoroutineDelay() = coroutineScope {

    launch {
        for (i in 0..5) {
            delay(400L)
            println(i)
        }
    }
    launch {
        for (i in 6..10) {
            delay(400L)
            println(i)
        }
    }

    println("Hello Coroutines")
}

private suspend fun runBlockingDelay() = runBlocking {
    launch {
        for (i in 0..5) {
            delay(400L)
            println(i)
        }
    }
    println("Hello Coroutines")
}

private suspend fun nestedCoroutineDelay() = coroutineScope {
    launch {
        println("Outer coroutine")
        launch {
            println("Inner coroutine")
            delay(400L)
        }
    }
    println("End of Main")
}

private suspend fun withoutJoin() = coroutineScope {
    launch {
        for (i in 1..5) {
            println(i)
            delay(400L)
        }
    }
    println("Start")
    println("End")
}

private suspend fun withJoin() = coroutineScope {
    val job = launch {
        for (i in 1..5) {
            println(i)
            delay(400L)
        }
    }

    println("Start")
    job.join() // ожидаем длительное завершения корутины
    println("End")
}

private suspend fun delayCoroutine() = coroutineScope {
    // корутина создана, но не запущена
    val job = launch(start = CoroutineStart.LAZY) {
        delay(200L)
        println("Coroutine has started")
    }

    delay(1000L)
    job.start() // запускаем корутину
    println("Other actions in main method")
}


private suspend fun asyncCoroutine() = coroutineScope {
    async { printHello() }
    println("Program has finished")
}

private suspend fun printHello() {
    delay(500L)  // имитация продолжительной работы
    println("Hello work!")
}


private suspend fun asyncCoroutineWithResult() = coroutineScope {
    val message: Deferred<String> = async { getMessage() }
    println("message: ${message.await()}")
    println("Program has finished")
}

suspend fun getMessage(): String {
    delay(500L)  // имитация продолжительной работы
    return "Hello"
}


private suspend fun severalAsyncWithResult() = coroutineScope {
    val numDeferred1 = async { sum(1, 2) }
    val numDeferred2 = async { sum(3, 4) }
    val numDeferred3 = async { sum(5, 6) }
    val num1 = numDeferred1.await()
    val num2 = numDeferred2.await()
    val num3 = numDeferred3.await()

    println("number1: $num1  number2: $num2  number3: $num3")
}

private suspend fun sum(a: Int, b: Int): Int {
    delay(500L) // имитация продолжительной работы
    return a + b
}


private suspend fun delayAsyncWithResult() = coroutineScope {
    // корутина создана, но не запущена
    val sum = async(start = CoroutineStart.LAZY) { sumDelay(1, 2) }

    delay(1000L)
    println("Actions after the coroutine creation")
    sum.start()
    println("sum: ${sum.await()}")   // запуск и выполнение корутины
}

private fun sumDelay(a: Int, b: Int): Int {
    println("Coroutine has started")
    return a + b
}

//
private suspend fun withoutDispatcher() = coroutineScope {

    launch {
        println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
    }
    println("Функция main выполняется на потоке: ${Thread.currentThread().name}")
}


private suspend fun withUnconfinedDispatcher() = coroutineScope {

    launch(Dispatchers.Default) {
        println("Поток корутины (до остановки): ${Thread.currentThread().name}")
        delay(500L)
        println("Поток корутины (после остановки): ${Thread.currentThread().name}")
    }

    println("Поток функции main: ${Thread.currentThread().name}")
}

@OptIn(ObsoleteCoroutinesApi::class, ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
private suspend fun withNamedDispatcher() = coroutineScope {

    launch(newSingleThreadContext("Custom Thread")) {
        println("Поток корутины: ${Thread.currentThread().name}")
    }
    println("Поток функции main: ${Thread.currentThread().name}")
}


private suspend fun canselCoroutine() = coroutineScope {
    val downloader: Job = launch {
        println("Начинаем загрузку файлов")
        for (i in 1..5) {
            println("Загружен файл $i")
            delay(500L)
        }
    }
    delay(800L)     // установим задержку, чтобы несколько файлов загрузились
    println("Надоело ждать, пока все файлы загрузятся. Прерву-ка я загрузку...")
    downloader.cancel()    // отменяем корутину
    downloader.join()
    downloader.cancelAndJoin()
    println("Работа программы завершена")
}

private suspend fun canselCoroutineWithException() = coroutineScope {
    val downloader: Job = launch {
        try {
            println("Начинаем загрузку файлов")
            for (i in 1..5) {
                println("Загружен файл $i")
                delay(500L)
            }
        } catch (e: CancellationException) {
            println("Загрузка файлов прервана")
        } finally {
            println("Загрузка завершена")
        }
    }
    delay(800L)
    println("Надоело ждать. Прерву-ка я загрузку...")
    downloader.cancelAndJoin()    // отменяем корутину и ожидаем ее завершения
    println("Работа программы завершена")
}


private suspend fun canselAsyncCoroutineWithException() = coroutineScope {
    // создаем и запускаем корутину
    val message = async {
        getMessageAsync()
    }
    // отмена корутины
    message.cancelAndJoin()

    try {
        // ожидаем получение результата
        println("message: ${message.await()}")
    } catch (e: CancellationException) {
        println("Coroutine has been canceled")
    }
    println("Program has finished")
}

suspend fun getMessageAsync(): String {
    delay(500L)
    return "Hello"
}


private suspend fun channelSetGet() = coroutineScope {
    val channel = Channel<Int>()
    launch {
        println("Поток отправлен: ${Thread.currentThread().name}")
        for (n in 1..5) {
            // отправляем данные через канал
            channel.send(n)
        }
    }
    // получаем данные из канала
    repeat(5) {
        val number = channel.receive()
        println("Поток получен: ${Thread.currentThread().name}")
        println(number)
    }
    println("End")
    println("Поток получен: ${Thread.currentThread().name}")
}


private suspend fun channelSetGetForMore() = coroutineScope {
    val channel = Channel<Int>()
    launch {
        for (n in 1..50000) {
            // отправляем данные через канал
            channel.send(n)
            println("Поток отправлен: ${Thread.currentThread().name}")
        }
    }
    // получаем данные из канала

    do {
        val number = channel.receive()
        println("Поток получен: ${Thread.currentThread().name}")
        println(number)

    } while (number != 0)

    println("End")
}

