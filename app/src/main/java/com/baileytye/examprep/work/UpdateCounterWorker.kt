package com.baileytye.examprep.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.baileytye.examprep.R
import com.baileytye.examprep.util.get
import com.baileytye.examprep.util.keyCounter
import com.baileytye.examprep.util.put

class UpdateCounterWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "UpdateCounterWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            val sharedPreferences = applicationContext.getSharedPreferences(
                applicationContext.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
            )
            var counter = sharedPreferences.get(keyCounter, 0)
            sharedPreferences.put(keyCounter, ++counter)

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

}