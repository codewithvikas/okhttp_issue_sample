package com.example.okhttpissuelistapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.okhttpissuelistapp.database.getDatabase
import com.example.okhttpissuelistapp.repository.OkHttpIssueRepository

class OfflineDataWork(context: Context,workParameters:WorkerParameters):CoroutineWorker(context,workParameters){

    companion object{
        val WORK_NAME = "offlineDataWork"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = OkHttpIssueRepository(database)
        try {
            repository.updateIssueList()
            return Result.success()
        }
        catch (e:Exception){
            return Result.retry()
        }
    }

}