package org.cobaltumapps

fun main() {
    val store = Store()
    val producer = Producer(store)
    val consumer = Consumer(store)

    Thread(producer).start()
    Thread(consumer).start()

}