package org.cobaltumapps

import java.util.concurrent.Semaphore

class Philosopher(
    private val semaphore: Semaphore,
    private val id: Int
): Thread() {
    private var num = 0

    override fun run() {
        try {

            while (num < 3) {
                semaphore.acquire()
                println("Філософ $id сідає за стіл")

                // філософ їсть
                sleep(500)
                num++
                println("Філософ $id виходе із-за стола")
                semaphore.release()

                // Філософ гуляє
                sleep(500)
            }
        } catch (e: InterruptedException) {
            println("У філософа $id проблемми")
        }
    }
}