package org.cobaltumapps

class Store {
    private val lock = Object()
    private var products = 0

    fun get() {
        synchronized(lock) {

            while (products < 1) {
                try {
                    lock.wait()
                } catch (_: InterruptedException) {}
            }

            products--
            println("Покупець купив 1 товар")
            println("Товарів на складі: $products")

            lock.notify()
        }

    }

    fun put() {
        synchronized(lock) {

            while (products >= 3) {
                try {
                    lock.wait()
                } catch (_: InterruptedException) { }
            }

            products++
            println("Виробник додав 1 товар")
            println("Товарів на складі: $products")

            lock.notify()
        }
    }

}