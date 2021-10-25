package com.example.okhttpissuelistapp.remote

import com.example.okhttpissuelistapp.database.OkHttpIssueEntity
import com.example.okhttpissuelistapp.database.UserDatabase
import com.squareup.moshi.Json

data class OkHttpIssueNetwork(
    val id:String,
    val title:String,
    val body:String?,
    @Json(name = "user")
    val userNetwork:UserNetwork,
    @Json(name = "updated_at")val updatedAt:String)


fun List<OkHttpIssueNetwork>.asDatabaseModel():Array<OkHttpIssueEntity>{
        return map {
            OkHttpIssueEntity(
                id = it.id,
                title = it.title,
                updatedAt = it.updatedAt,
                body = it.body,
                userDatabase = UserDatabase(it.userNetwork.userName,it.userNetwork.avatarUrl)
            )
        }.toTypedArray()
}
data class UserNetwork(@Json(name = "login")val userName: String, @Json(name = "avatar_url")val avatarUrl:String)