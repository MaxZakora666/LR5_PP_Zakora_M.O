package org.cobaltumapps

import java.util.concurrent.Semaphore

fun main() {
    val sem = Semaphore(2)

    for (x in 1 until 6) {
        Philosopher(sem, x).start()
    }
}

