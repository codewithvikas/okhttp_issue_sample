package com.example.okhttpissuelistapp.database

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.okhttpissuelistapp.domain.OkHttpIssue


@Entity(tableName = "okhttpIssueEntity")
data class OkHttpIssueEntity(
    @PrimaryKey
    val id:String,
    val title:String,
    val body:String?,
    val updatedAt:String,
    @Embedded val userDatabase:UserDatabase
    )

data class UserDatabase(val userName:String, val avatar_url:String)

fun List<OkHttpIssueEntity>.asDomainModel():List<OkHttpIssue>{
    return map {
        OkHttpIssue(
            title = it.title,
            body = it.body,
            avatarUrl = it.userDatabase.avatar_url,
            updatedAt = it.updatedAt,
            userName = it.userDatabase.userName
        )
    }
}



