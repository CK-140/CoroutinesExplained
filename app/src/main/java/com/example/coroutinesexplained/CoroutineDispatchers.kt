package com.example.coroutinesexplained

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main(){
    //Each coroutine has its own CoroutineScope and is not inherited
    //CoroutineContext can be inherited from parent coroutine
    //Dispatcher decides the thread of the coroutine
    runBlocking {
        //this child coroutine will inherit the coroutine context from parent i.e main
        launch {
            //Will remain confines to the inherited thread
            println("C1: ${Thread.currentThread().name}") //Thread main
            delay(1000)
            println("C2 after delay: ${Thread.currentThread().name}") // Thread main
        }


        //Default Dispatcher behaves like Globalscope
        //This will run on a separate background thread
        // gets its own context at global level
        launch(Dispatchers.Default) {
            println("C2: ${Thread.currentThread().name}") //Thread 1
            delay(1000) // Thread 1 is released
            println("C2 after delay: ${Thread.currentThread().name}") // Will not necessarily launch in Thread 1
        }

        launch(Dispatchers.Unconfined){
            //inherits coroutineContext initially from immediate parent but can change thread in future
            println("C3: ${Thread.currentThread().name}") //Thread 1
            delay(1000) // Thread 1 is released
            println("C3 after delay: ${Thread.currentThread().name}") // This will execute in some different thread
        }


        launch(Dispatchers.IO){
            println("C4: ${Thread.currentThread().name}") //Thread 1
            delay(1000) // Thread 1 is released
            println("C4 after delay: ${Thread.currentThread().name}")
        }


        launch(Dispatchers.Main){
            //Dispatchers.Main dispatcher is typically available in Android projects,
        // where it is associated with the main (UI) thread.
        }




    }
}