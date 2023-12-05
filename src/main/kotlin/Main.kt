import kotlinx.coroutines.*
import kotlin.system.*

fun main() {
    val time = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            println(getWeatherReport())
            println("Have a good day!")
        }

//        // For test why have to use async, Deferred and await
//        runBlocking {
//            var fore = ""
//            var temp = ""
//            launch {
//                fore = getForecast()
//            }
//            launch {
//                temp = getTemperature()
//            }
//            println("$fore $temp")
//        } => result nothing as fore, temp printed before get the value
    }
    println("Execution time: ${time/1000.0} seconds")
}
suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async {
        try {
            getTemperature()
        } catch (e: AssertionError) {
            println("Caught exception $e")
            "{No temperature found}"
        }
    }
    "${forecast.await()} ${temperature.await()}"
}
suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}
suspend fun getTemperature(): String {
    delay(500)
    throw AssertionError("Temperature is invalid")
    return "30\u00b0C"
}

