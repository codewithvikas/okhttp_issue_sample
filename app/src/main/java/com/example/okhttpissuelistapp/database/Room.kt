package com.example.okhttpissuelistapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface OkHttpIssueDao{

    @Query("Select * from okhttpIssueEntity")
    fun getOkHttpIssueList(): LiveData<List<OkHttpIssueEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg okHttpIssueEntity: OkHttpIssueEntity)
}

@Database(entities = [OkHttpIssueEntity::class],version = 1)
abstract class OkHttpIssueDatabase:RoomDatabase() {
    abstract val okHttpIssueDao:OkHttpIssueDao
}

private lateinit var  INSTANCE:OkHttpIssueDatabase

fun getDatabase(context: Context):OkHttpIssueDatabase{
    synchronized(OkHttpIssueDatabase::class){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext,OkHttpIssueDatabase::class.java,"okhttpissue").build()
        }
    }
    return INSTANCE
}