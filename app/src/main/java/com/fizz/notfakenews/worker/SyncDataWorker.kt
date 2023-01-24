package com.fizz.notfakenews.worker

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.fizz.notfakenews.util.makeStatusNotification

class SyncDataWorker(val context: Context,params:WorkerParameters):CoroutineWorker(context,params) {

    override suspend fun doWork(): Result {
        return try{
            Toast.makeText(context, "Fuck You", Toast.LENGTH_SHORT).show()
            return Result.success()
        }catch (throwable:Throwable){
            throwable.printStackTrace()
            Result.failure()
        }
    }

}