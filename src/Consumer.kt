package org.cobaltumapps

class Consumer(val store: Store): Runnable {

    override fun run() {
        for (x in 0 until 6) {
            store.get()
        }
    }
}