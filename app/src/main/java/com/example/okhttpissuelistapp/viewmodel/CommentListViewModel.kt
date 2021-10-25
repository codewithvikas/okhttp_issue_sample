package com.example.okhttpissuelistapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.okhttpissuelistapp.remote.Comment
import com.example.okhttpissuelistapp.remote.OkHttpApi
import kotlinx.coroutines.launch

class CommentListViewModel:ViewModel() {


    private val _commentList:MutableLiveData<List<Comment>?>
    val commentList:LiveData<List<Comment>?>
    get() = _commentList

    init {
        _commentList = MutableLiveData<List<Comment>?>()
        getCommentList(6880)
    }


    private fun getCommentList(issueId:Long){

        viewModelScope.launch {
            _commentList.value = OkHttpApi.okHttpIssueApiService.getCommentList(issueId)
        }

    }


}