package com.example.okhttpissuelistapp.remote

import com.squareup.moshi.Json
data class Comment(val id:Long,@Json(name = "created_at")val createdAt:String,val body:String,val user:UserNetwork)
