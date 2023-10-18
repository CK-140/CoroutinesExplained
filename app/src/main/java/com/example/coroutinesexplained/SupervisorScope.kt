package com.example.coroutinesexplained

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() = runBlocking {
    supervisorScope {
        val job1 = launch {
            try {
                delay(1000)
                println("Task 1 completed")
            } catch (e: CancellationException) {
                println("Task 1 cancelled: ${e.message}")
            }
        }

        val job2 = launch {
            try {
                delay(500)
                throw UnsupportedOperationException("Task 2 failed")
            } catch (e: Throwable) {
                println("Task 2 failed: ${e.message}")
            }
        }

        // Ensure that all child jobs are completed
        job1.join()
        job2.join()

        println("Both jobs executed")
    }
}




