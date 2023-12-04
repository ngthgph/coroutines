import kotlinx.coroutines.*
import kotlin.system.*

fun main() {
    val time = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            println(getWeatherReport())
            println("Have a good day!")
        }
    }
    println("Execution time: ${time/1000.0} seconds")
}
suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }
    "${forecast.await()} ${temperature.await()}"
}
suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}
suspend fun getTemperature(): String {
    delay(1000)
    return "30\u00b0C"
}

