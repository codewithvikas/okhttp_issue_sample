package com.example.okhttpissuelistapp

import android.app.Application
import android.os.Build
import androidx.work.*
import com.example.okhttpissuelistapp.work.OfflineDataWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class OkHttpIssueApplication:Application() {

   val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        initUpdateOkHttpIssueList()
    }

    private fun initUpdateOkHttpIssueList(){
        applicationScope.launch {
            setUpRecurringWork()
        }
    }


    private fun setUpRecurringWork(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .apply { if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                setRequiresDeviceIdle(true)
            }
            }.build()
        val repeatingWorkRequest = PeriodicWorkRequestBuilder<OfflineDataWork>(1,TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            OfflineDataWork.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingWorkRequest)


    }
}