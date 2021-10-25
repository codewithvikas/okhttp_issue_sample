package com.example.okhttpissuelistapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.okhttpissuelistapp.database.OkHttpIssueDatabase
import com.example.okhttpissuelistapp.database.getDatabase
import com.example.okhttpissuelistapp.domain.OkHttpIssue
import com.example.okhttpissuelistapp.repository.OkHttpIssueRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class OkHttpIssueViewModel(application: Application):AndroidViewModel(application) {



    val database:OkHttpIssueDatabase = getDatabase(application.applicationContext)
    val repository = OkHttpIssueRepository(database)
    val issuelist = repository.okHttpIssueList

    init {
        viewModelScope.launch {
            repository.updateIssueList()
        }
    }


    private val _navigateToIssueDetail = MutableLiveData<OkHttpIssue?>()
    val navigateToIssueNetworkDetail :LiveData<OkHttpIssue?>
        get() = _navigateToIssueDetail



    fun onListItemClicked(okHttpIssue: OkHttpIssue){
        _navigateToIssueDetail.value = okHttpIssue

    }
    fun navigatedToIssueDetail(){
        _navigateToIssueDetail.value = null
    }
    class Factory(val app:Application):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OkHttpIssueViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return OkHttpIssueViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to create viewModel")
        }

    }



}