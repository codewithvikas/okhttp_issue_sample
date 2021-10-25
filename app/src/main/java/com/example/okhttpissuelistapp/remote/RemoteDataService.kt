package com.example.okhttpissuelistapp.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


const val BASE_URL = "https://api.github.com/"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface OkHttpIssueService{
    @GET("repos/square/okhttp/issues")
     fun getIssueLis():Deferred<List<OkHttpIssueNetwork>>

    @GET("repos/square/okhttp/issues/{issueId}/comments")
     fun getCommentList(@Path("issueId")issueId:Long):Deferred<List<Comment>>
}

object OkHttpApi{
    val okHttpIssueApiService:OkHttpIssueService by lazy {
        retrofit.create(OkHttpIssueService::class.java)
    }
}

fun test(){
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        OkHttpApi.okHttpIssueApiService.getIssueLis()
    }

}