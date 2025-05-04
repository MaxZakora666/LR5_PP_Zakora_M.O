import kotlin.random.Random

class Car(
    var name: String,
    val station: AbstractFuelStation,
    val tankVolume: Int,
    val refuelCount: Int
) {
    fun run() {
        println("$name STARGED; Refuel count is $refuelCount")

        for (i in 0 until refuelCount) {
            val volume = Random.nextInt(tankVolume) + 1

            println("$name tries to get $volume lit. for ${i + 1} times")

            while (!station.tryRefuel(this, volume)) {
                println("$name is waiting")
                Thread.sleep(
                    Random.nextLong(1, 5) * 1000
                )
            }

            Thread.sleep(Random.nextLong(1, 3) * 1000)
            println("$name left the station for ${i + 1} times")
        }

        println("$name SAID GOOD-BYE")
    }
}