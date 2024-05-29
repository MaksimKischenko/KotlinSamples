package org.example.Lambda

typealias DoubleConversionLambda = (Int) -> Int

class LambdaExamples {
    inline fun calculateLambda(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
        return operation(a, b)
    }

    inline fun convertLambda(x: Double, converter: (Double) -> Double) : Double {
        val result = converter(x)
        println("$x is converted to $result")
        return result
    }

    //здесь inline не подойдет так как нет параметров func типа
    fun getConversionLambda(str: String): (Double) -> Double {
        println("")
        when (str) {
            "CentigradeToFahrenheit" -> {
                return { it * 1.8 + 32 }
            }
            "KgsToPounds" -> {
                return { it * 2.204623 }
            }
            "PoundsToUSTons" -> {
                return { it / 2000.0 }
            }
            else -> {
                return { it }
            }
        }
    }

    inline fun combineLambda(crossinline lambda1: (Int) -> Int, crossinline lambda2: (Int) -> Int): (Int) -> Int {
        return  { c: Int -> c * lambda1.invoke(10) + lambda2.invoke(10)}
    }

    fun combineLambdaWithConversion(lambda1:DoubleConversionLambda, lambda2: (Int) -> Int): DoubleConversionLambda {
        return  { c: Int -> c * lambda1.invoke(10) + lambda2.invoke(10)}
    }
}