package org.example.Flowimport kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

val database = listOf("Tom", "Bob", "Sam")



//TODO
//suspend fun getUsersCoroutine(): List<String> {
//    delay(3000L)  // имитация продолжительной работы
//    return listOf("Tom", "Bob", "Sam")
//}

//Одномоментное возвращение всего списка через coroutineScope
//suspend fun main() = coroutineScope<Unit>{
//    launch {
//        getUsersCoroutine().forEach { user -> println(user) }
//    }
//}



//TODO
//При этом при определении функции-потока (в данном случае функции getUsersFlow)
// необязательно использовать модификатор suspend.

//fun getUsersFlow(): Flow<String> = flow {
//    var i = 1;
//    for (item in database){
//        delay(400L) // имитация продолжительной работы
//        println("Emit $i item")
//        emit(item) // емитируем значение
//        i++
//    }
//}

// TODO
fun flowWithFlowOn(): Flow<Int> = flow {
    println("flowWithFlowOn: ${Thread.currentThread().name}")
    for (i in 1..3) {
        delay(1000)
        emit(i)
    }
}.flowOn(Dispatchers.Default)



// TODO
suspend fun fetchDataFromNetwork(): String {
    println("fetchDataFromNetwork: ${Thread.currentThread().name}")
    // Во время выполнения этой сопрограммы, контекст будет изменен на Dispatchers.IO
    return withContext(Dispatchers.IO) {
        println("withContext: ${Thread.currentThread().name}")
        // Здесь можно выполнять асинхронные операции ввода-вывода, такие как запросы к сети
        delay(1000)
        "Data from network"
    }
}


//TODO
//fun main() = runBlocking {
//    val sharedFlow = MutableSharedFlow<Int>(
//        replay = 0,
//        extraBufferCapacity = 0,
//        onBufferOverflow = BufferOverflow.DROP_LATEST
//    )
//
//    // Производитель элементов
//    val producer = launch {
//        for (i in 1..500000) {
//            delay(1)
//            sharedFlow.emit(i)
//        }
//    }
//    delay(1000)
//    val consumer1 = launch {
//        sharedFlow
//            .onEach { println("FIRST: $it") }
//            .collect()
//    }
////    delay(1000)
////    val consumer2 = launch {
////        sharedFlow
////            .onEach { println("SECOND: $it") }
////            .collect()
////    }
////    delay(1000)
////    val consumer3 = launch {
////        sharedFlow
////            .onEach { println("THIRD: $it") }
////            .collect()
////    }
//}





//TODO
//suspend fun main() {
////    getUsers().collect { user -> println(user) }
////
////
////    val userFlow = flow {
////        val usersList = listOf("Tom", "Bob", "Sam")
////        for (item in usersList){
////            emit(item)
////        }
////    }
////    userFlow.collect({user -> println(user)})
//
//    val numberFlow : Flow<Int> = flowOf(1, 2, 3, 5, 8)
//    numberFlow.collect{n -> println(n)}
//
//
//    val sum = numberFlow.reduce { accumulator, value ->
//        accumulator + value
//    }
//
//    val sum2 = numberFlow.fold(100) { accumulator, value ->
//        accumulator + value
//    }
//
//    println(sum)
//    println(sum2)
//
////    val userFlow = listOf("Tom", "Sam", "Bob").asFlow()
////    userFlow.collect({user -> println(user)})
//
//}




//suspend fun main() {
//
//
////    runBlocking {
////        val numbersFlow = flow {
////            (1..5).forEach {
////                delay(1000)
////                emit(it) }
////        }
////        launch {
////            numbersFlow.collect { println("Collector 1: Got $it") }
////        }
////        launch {
////            delay(2200)
////            numbersFlow.collect { println("Collector 2: Got $it") }
////        }
////    }
//
//    runBlocking {
//        val numbersFlow = MutableSharedFlow<Int>()
//        launch {
//            numbersFlow.collect { println("Collector 1: Got $it") }
//        }
//        launch {
//            delay(2200)
//            numbersFlow.collect { println("Collector 2: Got $it") }
//        }
//        launch {
//        }
//        launch {
//                    (1..5).forEach {
//                        delay(1000)
//                        numbersFlow.emit(it)
//                    }
//        }
//    }

//    runBlocking {
//        val numbersStateFlow = MutableStateFlow(0)
//
//        launch {
//            numbersStateFlow.collect { value ->
//                println("Коллектор 1: Получено значение $value")
//            }
//        }
//
//        launch {
//            delay(2200)
//            numbersStateFlow.collect { value ->
//                println("Коллектор 2: Получено значение $value")
//            }
//        }
//
//        launch {
//            (1..5).forEach { number ->
//                delay(1000)
//                numbersStateFlow.value = number
//            }
//        }
//    }
//    val numberFlow: Flow<Int> = (1..5).asFlow()
//
//    // combine()
//    val otherFlow: Flow<Int> = flowOf(10, 20, 30, 40, 50)
//    val combinedFlow: Flow<String> = numberFlow.combine(otherFlow) { a, b ->
//        "Number: $a, Other: $b"
//    }
//    combinedFlow.collect { println(it) }
//
//
//
//    // drop()
//    val droppedFlow: Flow<Int> = numberFlow.drop(2)
//    droppedFlow.collect { println(it) }
//
//    // filter()
//    val filteredFlow: Flow<Int> = numberFlow.filter { it % 2 == 0 }
//    filteredFlow.collect { println(it) }
//
//    // filterNot()
//    val filterNotFlow: Flow<Int> = numberFlow.filterNot { it % 2 == 0 }
//    filterNotFlow.collect { println(it) }
//
//    // filterNotNull()
//    val nullableFlow: Flow<Int?> = flowOf(1, null, 3, null, 5)
//    val filterNotNullFlow: Flow<Int> = nullableFlow.filterNotNull()
//    filterNotNullFlow.collect { println(it) }
//
//    // map()
//    val mappedFlow: Flow<String> = numberFlow.map { "Number: $it" }
//    mappedFlow.collect { println(it) }
//
//    // onEach()
//    val onEachFlow: Flow<Int> = numberFlow.onEach { println("Processing: $it") }
//    onEachFlow.collect { println(it) }
//
//    // take()
//    val takenFlow: Flow<Int> = numberFlow.take(3)
//    takenFlow.collect { println(it) }
//
//    // transform()
//    val transformedFlow: Flow<String> = numberFlow.transform { value ->
//        emit("Value: $value")
//        emit("Double Value: ${value * 2}")
//    }
//    transformedFlow.collect { println(it) }
//
//    // zip()
//    val zipFlow: Flow<String> = numberFlow.zip(otherFlow) { a, b ->
//        "Number: $a, Other: $b"
//    }
//    zipFlow.collect { println(it) }
