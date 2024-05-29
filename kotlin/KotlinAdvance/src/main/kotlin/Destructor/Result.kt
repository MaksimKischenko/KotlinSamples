package Destructor

import javax.net.ssl.SSLEngineResult

data class ResultForDestructor(val result: Int, val status: SSLEngineResult.Status)

fun functionOfDestructor(): ResultForDestructor {
    return ResultForDestructor(200, SSLEngineResult.Status.OK)
}


interface Messenger{
    fun send(message: String)
}

class InstantMessenger(val programName: String) : Messenger{
    override fun send(message: String){
        println("Message `$message` has been sent")
    }

    operator fun component1(): Any {
        return programName
    }
}
