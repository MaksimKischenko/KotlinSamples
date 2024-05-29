package Ktor

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.URL
import kotlin.system.measureTimeMillis

private const val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
private const val FLIGHT_ENDPOINT = "$BASE_URL/flight"
private const val LOYALTY_ENDPOINT = "$BASE_URL/loyalty"


private suspend fun main() {
//    fetchFlight()
//    fetchFlightAsync().also { println(it) }
//    raceFunc()

    runBlocking {
        println("Getting the latest flight info...")
        val flights = fetchFlights()
        val flightDescriptions = flights.joinToString {
            it
        }
        println("Found flights for $flightDescriptions")
        flights.forEach {
            println( "END: " + watchFlight(it).collect({ println(it) }))
        }
    }
}


//последовательное вычиление 13 секунд
suspend fun fetchFlight() = runBlocking {
//    println("STARTED MAIN CODE")
//    println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
    launch(Dispatchers.IO) {
//        println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
        val executionTime = measureTimeMillis {
            URL(FLIGHT_ENDPOINT).readText()
            val client = HttpClient(CIO)
            val flightResponse = client.get<String>(FLIGHT_ENDPOINT)
            val loyaltyResponse = client.get<String>(LOYALTY_ENDPOINT)
            println("$flightResponse\n$loyaltyResponse")
        }
//        println("Время выполнения операций: $executionTime мс")
    }
//    println("MAKING MAIN CODE")
}


//параллельное вычисление - 6 секунд
suspend fun fetchFlightAsync(): String = coroutineScope {
    lateinit var result: String
    val executionTime = measureTimeMillis {
        val client = HttpClient(CIO)
        val flightResponse = async {
//            println("Started fetching flight info")
//            println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
            client.get<String>(FLIGHT_ENDPOINT).also {
//                println("Finished fetching flight info")
            }
        }
        val loyaltyResponse = async {
//            println("Started fetching loyalty info")
//            println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
            client.get<String>(LOYALTY_ENDPOINT).also {
//                println("Finished fetching loyalty info")
            }
        }
        delay(500) //необходима преостановка
//        println("Combining flight data")
        result = "${flightResponse.await()}\n${loyaltyResponse.await()}"
    }
//    println("Время выполнения операций: $executionTime мс")
    result
}
val passengersPerFlight = 75
val numberOfFlights = 1000
var checkedInPassengers = 0 //atomic(0) // потокобезопасно

 fun raceFunc() {
     runBlocking {
        repeat(numberOfFlights) {
            launch(Dispatchers.Default) {
                println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
                checkedInPassengers += passengersPerFlight
            }
        } }
    println(checkedInPassengers)
}



fun watchFlight(initialFlight: String) : Flow<String> {
    return flow {
        var flight = initialFlight
        repeat(5) {
            emit(flight)
            delay(1000)
            flight += "1"
        } }
}


suspend fun fetchFlights(
    passengerNames: List<String> = listOf("Madrigal", "Polarcubis")
) = passengerNames.map { it + fetchFlightAsync() }
