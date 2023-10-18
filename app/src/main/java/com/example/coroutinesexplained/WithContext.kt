package com.example.coroutinesexplained

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    val originalContext = coroutineContext

    launch {
        println("Before withContext: $coroutineContext")

        withContext(Dispatchers.IO) {
            // Inside withContext: the coroutine switches to IO dispatcher
            println("Inside withContext: $coroutineContext")
        }

        // After withContext: the coroutine reverts to its original context
        println("After withContext: $coroutineContext")
    }

    // Outside the launch: the coroutine context remains the same
    println("Outside launch: $originalContext")

}
