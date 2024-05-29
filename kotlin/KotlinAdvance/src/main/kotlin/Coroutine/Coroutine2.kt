package org.example.Coroutine
import kotlinx.coroutines.*
import java.io.IOException
import javax.security.auth.callback.Callback



fun main() {
    println("Поток1: ${Thread.currentThread().name}")
    runBlocking(Dispatchers.Default) {
        println("Поток2: ${Thread.currentThread().name}")
        val job: Job = launch(Dispatchers.Default) {
            while (true) {
                println("Поток3: ${Thread.currentThread().name}")
                delay(10)
            }
        }
        delay(60)
        job.cancel()
    }
}

//    runBlocking {
//        val slow: Deferred<Int> = async {
//            var result = 0
//            delay(1000)
//            for (i in 1..10) {
//                result += i
//            }
//            println("Call complete for slow: $result")
//            result
//        }
//
//        val quick: Deferred<Int> = async {
//            delay(100) // имитируем быструю фоновую работу printin("Call complete for quick: 5")
//            5
//        }
//        val result: Int = quick.await() + slow.await()
//        println(result)
//
//    }


//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        try {
//            while (true) {
//                if (System.currentTimeMillis() >= nextPrintTime) {
//                    println("job: I'm working..")
//                    nextPrintTime += 500
//                }
//                ensureActive()
//            }
//
//        } catch (e: CancellationException) {
//            println(e)
//        }
//    }
//    delay(1200)
//    println("main: I'm going to cancel this job")
//    job.cancel()
//    println("main: Done")
//


//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        while (true) {
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("job1: I'm working..")
//                nextPrintTime += 500
//            }
//            yield()
//        }
//    }
//
//    delay(1200)
//
//    val job2 = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        while (true) {
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("job2: I'm working..")
//                nextPrintTime += 500
//            }
//            yield()
//        }
//    }
//    println("main: I'm going to cancel this job")
//    job.cancel()
//
//    println("main: Done")


//    runBlocking {
//        val job = launch {
//            val child1 = launch {
//                delay(Long.MAX_VALUE)
//            }
//
//            val child2 = launch {
//                child1.join()
//                println("Child 1 is cancelled")
//                delay(100)
//                println("Child 2 is still alive!")
//            }
//
//            println("Cancelling child 1..")
//
//            child1.cancel()
//            child2.join()
//            println("Parent is not cancelled")
//        }
//        job.join()
//    }
//}
//
//fun <Т> runBlocking (
//// аргументы функции удалены для краткости
//    block: suspend CoroutineScope.() -> Т): Т {
//
//}
