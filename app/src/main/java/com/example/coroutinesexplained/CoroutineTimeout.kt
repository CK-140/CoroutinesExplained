package com.example.coroutinesexplained

import kotlinx.coroutines.runBlocking

fun main(){

    runBlocking {
        println("Main Program starts: ${Thread.currentThread().name}")





        println("Main Program ends: ${Thread.currentThread().name}")

    }
}