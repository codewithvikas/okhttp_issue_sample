package com.example.okhttpissuelistapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.okhttpissuelistapp.database.OkHttpIssueDatabase
import com.example.okhttpissuelistapp.database.asDomainModel
import com.example.okhttpissuelistapp.domain.OkHttpIssue
import com.example.okhttpissuelistapp.remote.OkHttpApi
import com.example.okhttpissuelistapp.remote.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OkHttpIssueRepository(private val okHttpIssueDatabase: OkHttpIssueDatabase) {

    val okHttpIssueList:LiveData<List<OkHttpIssue>> = Transformations.map(okHttpIssueDatabase.okHttpIssueDao.getOkHttpIssueList()){
        it.asDomainModel()
    }

    suspend fun updateIssueList(){
        withContext(Dispatchers.IO){
            try {
                val okHttpIssueNetwork = OkHttpApi.okHttpIssueApiService.getIssueLis()
                okHttpIssueDatabase.okHttpIssueDao.insertAll(*okHttpIssueNetwork.asDatabaseModel())
            }
            catch (e:Exception){
                e.printStackTrace()
            }

        }

    }

}