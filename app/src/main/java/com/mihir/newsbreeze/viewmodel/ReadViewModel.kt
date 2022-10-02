package com.mihir.newsbreeze.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mihir.newsbreeze.data.local.NewsDatabase
import com.mihir.newsbreeze.data.model.Article
import com.mihir.newsbreeze.data.repo.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReadViewModel(application: Application): AndroidViewModel(application)  {
    private val dao by lazy { NewsDatabase.getDatabase(application).NewsDao() }
    private val repo by lazy { NewsRepo(dao) }

    fun addNewsToSaved(news: Article){
        viewModelScope.launch(Dispatchers.IO){
            repo.addNews(news)
        }
    }
}