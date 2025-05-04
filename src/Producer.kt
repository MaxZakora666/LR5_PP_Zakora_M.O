package org.cobaltumapps

class Producer(val store: Store): Runnable {

    override fun run() {
        for (x in 0 until 6) {
            store.put()
        }
    }
}