package sync_semaphore

import AbstractFuelStation
import Car
import java.util.concurrent.Semaphore
import java.util.concurrent.locks.ReentrantLock

class FuelStationSemaphore(reserve: Int = 1000, columnCount: Int
): AbstractFuelStation(reserve, columnCount) {

    private val mutex = ReentrantLock() // Аналог Mutex C#
    private var semaphore: Semaphore = Semaphore(columnCount) // Аналог SemaphoreSlim

    override fun fill(amount: Int) {
        mutex.lock()
        if (amount + reserve > capacity)
            reserve = capacity
        else
            reserve += capacity

        println("STATION TANK REFUELED")
        mutex.unlock()
    }

    override fun tryRefuel(car: Car, volume: Int): Boolean {
        semaphore.acquire()
        mutex.lock()
        var result = false

        if (volume <= reserve) {
            println("Fuels reserve is $reserve. $volume liters fueling began for ${car.name}")
            reserve -= volume
            mutex.unlock()
            Thread.sleep(100L * volume)

            println("${car.name} fueling finished")
            result = true
        }
        else {
            println("Fuel reserve $reserve is insufficient for ${car.name}")
            mutex.unlock()
        }
        semaphore.release()
        return result
    }

}