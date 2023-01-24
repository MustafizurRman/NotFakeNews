package com.fizz.notfakenews.worker

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.*
import java.util.concurrent.TimeUnit

class WorkManagerUtils {
    fun syncData(context:Context){
        Toast.makeText(context, "inside you mf", Toast.LENGTH_SHORT).show()
        val workManager= WorkManager.getInstance(context)
        val constraints=Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).setRequiredNetworkType(NetworkType.METERED).setRequiresStorageNotLow(true).build()
        val periodicWorkRequest= PeriodicWorkRequest.Builder(SyncDataWorker::class.java,15,TimeUnit.MINUTES).setInitialDelay(1,TimeUnit.MINUTES)
        val periodicWorkRequestBuilder=periodicWorkRequest.setConstraints(constraints).addTag("Syncing your Data").build()
        Log.e("Work","WorkerStarted")

//        val request= PeriodicWorkRequestBuilder<SyncDataWorker>(15,TimeUnit.MINUTES).setConstraints(constraints).build()
        workManager.enqueueUniquePeriodicWork("Syncing Data",ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequestBuilder)
    }
}