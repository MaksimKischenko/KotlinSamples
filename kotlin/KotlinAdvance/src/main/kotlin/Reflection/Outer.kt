package Reflection

import javax.net.ssl.SSLEngineResult

class Outer {
    inner class Inner(val age:Int)
}

data class ReflectionExample(val result: Int, val status: SSLEngineResult.Status)
val reflector = ReflectionExample(200, SSLEngineResult.Status.OK )




fun isOdd(x: Int) = x % 2 != 0