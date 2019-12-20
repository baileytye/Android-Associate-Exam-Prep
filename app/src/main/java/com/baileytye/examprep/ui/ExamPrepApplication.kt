package com.baileytye.examprep.ui

import android.app.Application
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
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
                1, TimeUnit.HOURS
            ).setConstraints(constraints)
                .build()   //Can pass data with setData of type Data. Key value pair

            WorkManager.getInstance(this@ExamPrepApplication).enqueueUniquePeriodicWork(
                UpdateCounterWorker.WORK_NAME, ExistingPeriodicWorkPolicy.REPLACE, repeatRequest
            )
        }
    }
}