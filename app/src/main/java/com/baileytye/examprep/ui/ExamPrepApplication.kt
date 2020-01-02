package com.baileytye.examprep.ui

import android.app.Application
import androidx.work.*
import com.baileytye.examprep.work.UpdateCounterWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ExamPrepApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            val constraints = Constraints.Builder()
                .setRequiresBatteryNotLow(true).build()

            val repeatRequest = PeriodicWorkRequestBuilder<UpdateCounterWorker>(
                1, TimeUnit.DAYS
            ).setConstraints(constraints)
                .build()   //Can pass data with setData of type Data. Key value pair

            //This is only required to prevent crash in testing
            WorkManager.initialize(this@ExamPrepApplication, Configuration.Builder().build())

            WorkManager.getInstance(this@ExamPrepApplication).enqueueUniquePeriodicWork(
                UpdateCounterWorker.WORK_NAME, ExistingPeriodicWorkPolicy.REPLACE, repeatRequest
            )
        }
    }
}