package org.example

import Channel.Item
import Channel.consumeItems
import Channel.getItems
import Destructor.InstantMessenger
import Destructor.functionOfDestructor
import Object.Game
import Object.ObjectExample
import Reflection.Outer
import Reflection.isOdd
import Reflection.reflector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.Extension.addEnthusiasm
import org.example.Extension.narrate
import org.example.Extension.print
import org.example.Functions.ActionUser
import org.example.Functions.UserCallback
import org.example.Lambda.LambdaExamples
import org.example.Models.User
import Сompleteness.ViewState
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

fun main() {
//    reflectionAction()
//    lambdaAction()
//    destructorAction()
//    extensionAction()
//    scopeFunctions()
//    objectAction()
//    callBackAction()
//    simpleChannelAction()
}


fun simpleChannelAction() = runBlocking{
    println("THREAD: ${Thread.currentThread().name}")
    val channel = Channel<Item>()
    launch(Dispatchers.Default) {
        println("THREAD: ${Thread.currentThread().name}")
        getItems(channel)
    }
    println("THREAD: ${Thread.currentThread().name}")
    consumeItems(channel)
}

fun parallelismAction() {
    // Создание LinkedBlockingQueue с ограничением в 5 элементов
    val workQueue = LinkedBlockingQueue<Int>(5)

    val producer = thread {
        while (true) {
            workQueue.put(1)
            println("Producer added a new element to the queue")
        }
    }

    val consumer = thread {
        while (true) {
            Thread.sleep(1000)
            workQueue.take()
            println("Consumer took an element out of the queue")
        }
    }


}



fun extensionAction() {
    val text = "New"
    text.addEnthusiasm()
    text.print()
    narrate("Hello") { it.uppercase() }
}

fun destructorAction() {
    val (result, status) = functionOfDestructor()
    println(result)

    //component1()
    val (name) = InstantMessenger(programName = "TEST")
    println(name)

    //component1()
    val nums = listOf(1, 2, 3)
    operator fun Int.component1(): Any {
        return nums.forEach {
            println("GO")
        }
    }
    for ((a) in nums) {
        println((a))
    }

    //get
    val matrix = listOf(
        listOf("Apple", "Banana", "Cherry"),
        listOf("Dog", "Elephant", "Fox"),
        listOf("Grapes", "Horse", "Icecream")
    )
    // Получаем элемент из матрицы по координатам (100, 100)
    val element = matrix[0]
    println(element)
}
fun lambdaAction() {
    val lambdaExamples = LambdaExamples()
    val sum = lambdaExamples.calculateLambda(5, 3) { x, y -> x + y }
    println(sum)

    val sum2 = lambdaExamples.convertLambda(10.0) { it * 1.8 + 32 }
    val sum2Copy = lambdaExamples.convertLambda(10.0, converter = { c: Double -> c* 1.8 + 32 })
    println(sum2)

    val pounds = lambdaExamples.getConversionLambda("KgsToPounds")(2.5)
    println(pounds)

    val lambda1: (Int) -> Int = { x -> x * 10 }
    val lambda2: (Int) -> Int = { y -> y * 10 }

    val result = lambdaExamples.combineLambda(lambda1, lambda2)
    println(result.invoke(10))

    val resultCopy =  lambdaExamples.combineLambdaWithConversion(lambda1, lambda2)
    println(resultCopy.invoke(10))
}


fun reflectionAction() {
    val o = Outer()
    val boundInnerCtor = o::Inner
    println(boundInnerCtor)

    val getObj = ::reflector.get()
    println(::reflector.get())
    println(::reflector.name)


    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd))
}

fun renderState(viewState: ViewState) {
    when (viewState) {
        is ViewState.Loading -> println("Loading")
        is ViewState.Success -> println("DATA: ${viewState.data}")
        is ViewState.Error -> println("ERROR: ${viewState.message}")
    }
}

fun objectAction() {
    val game = Game
    val gameNew = Game

    val objExample = ObjectExample()
    objExample.printX()
}