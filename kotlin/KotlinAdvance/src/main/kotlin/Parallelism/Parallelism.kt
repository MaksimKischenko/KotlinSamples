package org.example.Parallelism

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.sql.Connection
import java.sql.DriverManager
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

private val myConnection =
    object : ThreadLocal<Connection>() {
        override fun initialValue(): Connection? {
            return DriverManager.getConnection("")
        }
    }


class Parallelism {
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

class OperationSimple {
    var aList: MutableList<Int> = ArrayList()
        private set

    fun add() {
        val last = aList.lastOrNull() ?: 0
        aList.add(last + 1)
    }

    init {
        aList.add(1)
    }
}

class OperationSynchronizedCollections {
    var aList = Collections.synchronizedList<Int>(object : ArrayList<Int?>() {
        init {
            add(1)
        }
    })

    fun add() {
        val last = aList.last()
        aList.add(last + 1)
    }
}
class OperationSynchronized {
    val aList: MutableList<Int> = mutableListOf(1)

    @Synchronized
    fun add() {
        val last = aList.last()
        aList.add(last + 1)
    }
}

class OperationMutex {
    var aList: MutableList<Int> = ArrayList()
        private set

    private val mutex = Mutex()

    suspend fun add() {
        mutex.withLock {
//            val last = aList.lastOrNull() ?: 0
//            aList.add(last + 1)
//
            //TODO copy List
            val tempList = ArrayList(aList)
            val last = tempList.lastOrNull() ?: 0
            tempList.add(last + 1)
            aList = tempList
        }
    }
}

class SharedResource {
    var counter = 0
    private val mutex = Mutex()

    suspend fun increment() {
        mutex.withLock {
            counter++
        }
    }
}

suspend fun mutexRun() {
    val sharedResource = SharedResource()
    coroutineScope {
        repeat(100) {
            launch {
                println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
                sharedResource.increment()
            }
        }
    }

    println("Final counter value: ${sharedResource.counter}")
}

fun errorRun() {
    val instance = OperationSynchronized()

    // Первый поток
    Thread {
        println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
        for (i in 1..5) {
            instance.add()
            println("${Thread.currentThread().name}: ${instance.aList}")
            Thread.sleep(100)
        }
    }.start()

    // Второй поток
    Thread {
        println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
        for (i in 1..5) {
            instance.add()
            println("${Thread.currentThread().name}: ${instance.aList}")
            Thread.sleep(150)
        }
    }.start()

    // Третий поток
    Thread {
        println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
        for (i in 1..5) {
            instance.add()
            println("${Thread.currentThread().name}: ${instance.aList}")
            Thread.sleep(200)
        }
    }.start()
}

suspend fun runBlocking() {
    val instance = OperationSimple()

    kotlinx.coroutines.runBlocking {
        // Первый поток
        launch(Dispatchers.Default) {
            repeat(5) {
                instance.add()
                println("${Thread.currentThread().name}: ${instance.aList}")
                delay(100)
            }
        }
    }
    kotlinx.coroutines.runBlocking {
        // Второй поток
        launch(Dispatchers.Default) {
            repeat(5) {
                instance.add()
                println("${Thread.currentThread().name}: ${instance.aList}")
                delay(150)
            }
        }
    }
    kotlinx.coroutines.runBlocking {
        // Третий поток
        launch(Dispatchers.Default) {
            repeat(5) {
                instance.add()
                println("${Thread.currentThread().name}: ${instance.aList}")
                delay(200)
            }
        }
    }
}

suspend fun runBlockingMutex() {
    val instance = OperationMutex()
    kotlinx.coroutines.runBlocking {
        // Первый поток
        val job1 = launch(Dispatchers.Default) {
            repeat(5) {
                instance.add()
                println("JOB1: ${Thread.currentThread().name}: ${instance.aList}")
                delay(100)
            }
        }
        // Второй поток
        val job2 = launch(Dispatchers.Default) {
            repeat(5) {
                instance.add()
                println("JOB2: ${Thread.currentThread().name}: ${instance.aList}")
                delay(150)
            }
        }
        // Третий поток
        val job3 = launch(Dispatchers.Default) {
            repeat(5) {
                instance.add()
                println("JOB3: ${Thread.currentThread().name}: ${instance.aList}")
                delay(200)
            }
        }
    }
}

fun runBlockingSynchronized() {
    val instance = OperationSynchronized()
    kotlinx.coroutines.runBlocking {
        val mutex = Mutex()
        // Первый поток
        val job1 = launch(Dispatchers.Default) {
            repeat(5) {
                mutex.withLock {
                    instance.add()
                    println("JOB1: ${Thread.currentThread().name}: ${instance.aList}")
                }
                delay(100)
            }
        }
        // Второй поток
        val job2 = launch(Dispatchers.Default) {
            repeat(5) {
                mutex.withLock {
                    instance.add()
                    println("JOB2: ${Thread.currentThread().name}: ${instance.aList}")
                }
                delay(150)
            }
        }
        // Третий поток
        val job3 = launch(Dispatchers.Default) {
            repeat(5) {
                mutex.withLock {
                    instance.add()
                    println("JOB3: ${Thread.currentThread().name}: ${instance.aList}")
                }
                delay(200)
            }
        }
    }
}


