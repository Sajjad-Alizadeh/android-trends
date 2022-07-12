package com.sajjad.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sajjad.coroutine.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.coroutines.coroutineContext
import kotlin.system.measureTimeMillis

class CoroutineActivity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityCoroutineBinding

    //job
    private lateinit var job: Job

    //tag
    private val TAG = "CoroutineActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //init binding
        binding = ActivityCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "application is running...")
        //coroutine
        /* normal
        CoroutineScope(Dispatchers.IO).launch {
            val result1 = doTask1()
            val result2 = doTask2()

            Log.d(TAG, result1)
            Log.d(TAG, result2)

            // log command will be executing after 7 second
        }

         */
        /* async
        CoroutineScope(Dispatchers.IO).launch {
            val result1 = async { doTask1() }
            val result2 = async { doTask2() }

            var timeInMls = ""

            val time = measureTimeMillis {
                timeInMls = "${result1.await()} ${result2.await()}"
            }

            Log.d(TAG, "$timeInMls is run at: $time")

            //the log command will be executing after 5 second. because the 5 second is more than 2 second :)
        }

         */
        /* withContext
        CoroutineScope(IO).launch {
            callFakeApiRequest().also { userName ->
                withContext(Main) {
                    binding.tvStatus.text = userName
                }
            }
        }

         */
        /*
        repeat
        CoroutineScope(IO).launch {
            repeat(5) {
                Log.d(TAG, "do task 1")
                delay(1000)
            }
        }

        */
        /* timeout
        CoroutineScope(IO).launch {
            withTimeoutOrNull(5000) {
                for (i in 1..10) {
                    Log.d(TAG, i.toString())
                    delay(1000)
                }
            }
        }

         */
        /* job
        job = CoroutineScope(Main).launch {
            delay(3000)
            Log.d(TAG, "task 1 donned!")
        }
        //job.cancel()
        CoroutineScope(Main).launch {
            delay(3500)
            Log.d(TAG, "job is Active: ${job.isActive}")
            Log.d(TAG, "job is complete: ${job.isCompleted}")
            Log.d(TAG, "job is cancelled: ${job.isCancelled}")
        }

         */
        //join
        CoroutineScope(Main).launch {

            val job = CoroutineScope(Main).launch {
                repeat(5) {
                    Log.d(TAG, "calculating...")
                    delay(1000)
                }
            }
            delay(5000)
            job.cancelAndJoin()
            Log.d(TAG,"done!")
        }

    }

    private suspend fun callFakeApiRequest(): String {
        showThreadName("callFakeApiRequest")
        delay(3000)
        return "Username: Sajjad"
    }

    private suspend fun doTask1(): String {
        delay(2000)
        return "doingTask1"
    }

    private suspend fun doTask2(): String {
        delay(5000)
        return "doingTask2"
    }

    private fun showThreadName(methodName: String) {
        Log.d(TAG, "$methodName is executing at thread: ${Thread.currentThread().name}")
    }

}