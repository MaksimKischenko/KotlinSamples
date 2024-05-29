package org.example.Extension

inline fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

fun String.addEnthusiasm(enthusiasmLevel: Int = 1) =
    this + "!".repeat(enthusiasmLevel)

fun <T> T.print(): T {
    println(this)
    return this
}

inline fun narrate(
    message: String,
    modifier: (String) -> String = { "" } ){
      println(modifier(message))
}


operator fun List<List<String>>.get(coordinate: Int) =
    getOrNull(coordinate + 100)?.getOrNull(coordinate + 100)
