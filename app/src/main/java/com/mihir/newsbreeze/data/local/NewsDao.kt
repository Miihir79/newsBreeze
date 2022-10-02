package com.mihir.newsbreeze.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mihir.newsbreeze.data.model.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNews(note: Article)

    @Query("SELECT * from news_table")
    fun readAllNews():LiveData<List<Article>>

    // to retrieve a news article from id
    @Query("Select * from news_table where id is :sentId")
    fun sendNews(sentId:Int): Article

    @Delete
    suspend fun deleteNews(news: Article)
}