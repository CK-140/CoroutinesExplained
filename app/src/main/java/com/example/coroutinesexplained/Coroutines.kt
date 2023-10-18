package com.example.coroutinesexplained

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis


fun main() {

    runBlocking{
        val originalContext = coroutineContext
        launch(Dispatchers.Default) {
            print(this)
        }
            // This code runs in the Default dispatcher context
            val result = withContext(Dispatchers.IO) {
                print(this)
                // This code runs in the IO dispatcher context
                // Perform some IO-bound work here
                "Result from IO"
            }
            // Now we're back in the Default dispatcher context
            println("Result: $result")
        }

}

suspend fun getMessage1(): String{
    delay(1000)
    return "Hello"
}

suspend fun getMessage2(): String{
    delay(1000)
    return "WOrld"
}