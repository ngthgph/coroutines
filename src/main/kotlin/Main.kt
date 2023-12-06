import kotlinx.coroutines.*
import kotlin.system.*

fun main() {
    runBlocking {
        launch {
            delay(1000)
            println("10 results found")
        }
        println("Loading...")
    }
}