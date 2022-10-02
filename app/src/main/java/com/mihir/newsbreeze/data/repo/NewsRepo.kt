package com.mihir.newsbreeze.data.repo

import androidx.lifecycle.LiveData
import com.mihir.newsbreeze.data.local.NewsDao
import com.mihir.newsbreeze.data.model.Article

class NewsRepo(private val dao:NewsDao) {

    val readAllData: LiveData<List<Article>> = dao.readAllNews()

    suspend fun addNews(news: Article){
        dao.addNews(news)
    }

    //to add the functionality to delete the saved news
   suspend fun deleteNews(news: Article){
        dao.deleteNews(news)
   }
}