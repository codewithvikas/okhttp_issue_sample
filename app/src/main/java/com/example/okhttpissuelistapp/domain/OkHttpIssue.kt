package com.example.okhttpissuelistapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OkHttpIssue(
    val title:String,
    val body:String,
    val userName:String,
    val avatarUrl:String,
    val updatedAt:String):Parcelable{
        val shortDescription:String
        get() = body.take(200)
    }
