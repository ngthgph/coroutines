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
    val temperature = async { getTemperature() }

    delay(2000)
    temperature.cancel()

    "${forecast.await()} "
}
suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}
suspend fun getTemperature(): String {
    delay(500)
    return "30\u00b0C"
}

