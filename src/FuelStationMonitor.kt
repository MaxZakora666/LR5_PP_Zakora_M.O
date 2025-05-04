package sync_monitor

import AbstractFuelStation
import Car

class FuelStationMonitor(reserve: Int, columnCount: Int): AbstractFuelStation(reserve, columnCount) {
    private var lockObject = Object()
    private var freeColumnCount = columnCount

    override fun fill(amount: Int) {

        synchronized(lockObject) {
            if (amount + reserve > capacity)
                reserve = capacity
            else
                reserve += capacity

            println("STATION TANK REFUELED")
            lockObject.notifyAll()
        }

    }

    override fun tryRefuel(car: Car, volume: Int): Boolean {
        var result = false

        synchronized(lockObject) {
            while (freeColumnCount <= 0)
                lockObject.wait()

            if (volume <= reserve) {
                freeColumnCount--
                println("Fuels reserve is $reserve. $volume liters fueling began for ${car.name}")

                reserve -= volume
                result = true
            }
            else println("Fuel reserve $reserve is insufficient for ${car.name}")
        }
        if (result) {

            Thread.sleep(100L * volume)
            println("${car.name} fueling finished")
            synchronized(lockObject) {
                freeColumnCount++
                lockObject.notifyAll()
            }
        }
        return result
    }

}