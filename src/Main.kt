import sync_monitor.FuelStationMonitor
import kotlin.concurrent.thread
import kotlin.random.Random

fun main() {
    val carCount = 5
    println("START")

    val station: AbstractFuelStation = FuelStationMonitor(2, 300)
    val threads = mutableListOf<Thread>()

    repeat(carCount) { i ->
        val tank = Random.nextInt(30) + 20
        val attempts = Random.nextInt(3) + 1
        val car = Car("Car#${i + 1}", station, tank, attempts)
        threads.add(thread { car.run() })
    }

    Thread.sleep(3000)
    station.fill(200)

    threads.forEach { it.join() }
    println("FINISHED")
}