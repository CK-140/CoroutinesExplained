package com.example.coroutinesexplained

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

//Sequential by default
//Can make them parallel
//lazy execution
fun main(){
    //Execution flow is sequential by default inside a coroutine
    //Can make them concurrent with async
    runBlocking {
        println("Main Program starts: ${Thread.currentThread().name}")

        //these are executing sequentially, taking two seconds

//        val time = measureTimeMillis {
//
//            //time taken here is 2 seconds
//            val messageOne = getMessageOne()
//            val messageTwo = getMessageTwo()
//
//            println("Full message : ${messageOne + messageTwo}")
//        }


        //These are executing concurrently taking 1 second
        val time = measureTimeMillis {
            val messageOne = async { getMessageOne() }
            val messageTwo = async { getMessageTwo()}

            println("Full message : ${messageOne.await() + messageTwo.await()}")
        }


        println("Completed in time : $time ms")
        println("Main Program ends: ${Thread.currentThread().name}")

    }


}

suspend fun getMessageOne(): String{
    delay(1000)
    return "Hello"
}

suspend fun getMessageTwo(): String{
    delay(1000)
    return "World"
}