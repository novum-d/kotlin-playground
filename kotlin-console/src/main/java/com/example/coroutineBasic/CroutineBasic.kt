package com.example.coroutineBasic

import kotlinx.coroutines.*

fun main() {
    launchCoroutine() // run safe
    runThread() // java.lang.OutOfMemoryError
}

private fun launchCoroutine() = runBlocking {
    repeat(100_000) { // launch a lot of coroutines
        launch {
            delay(5000L)
            print(".")
        }
    }
}

private fun runThread() {
    repeat(100_000) { // launch a lot of threads
        object : Thread() {
            override fun run() {
                sleep(5000L)
                print(".")
            }
        }.start()
    }
}


