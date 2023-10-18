package com.example.coroutinesexplained

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){

    runBlocking {
        println("Main Program starts: ${Thread.currentThread().name}")
        val job: Job = launch(Dispatchers.Default) {

            println("Inside Thread: ${Thread.currentThread().name}")
            for(i in 0..500){
                if(!isActive) return@launch
                print("$i")
                delay(1)
            }
        }
        delay(10)
        job.cancelAndJoin()
        println("Main Program ends: ${Thread.currentThread().name}")
    }
}