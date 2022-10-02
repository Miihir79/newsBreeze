package com.mihir.newsbreeze.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mihir.newsbreeze.data.local.NewsDatabase
import com.mihir.newsbreeze.data.model.Article
import com.mihir.newsbreeze.data.repo.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedNewsViewModel(application: Application): AndroidViewModel(application) {
    private val dao by lazy { NewsDatabase.getDatabase(application).NewsDao() }
    private val repo by lazy { NewsRepo(dao) }
    val allSavedNewsLiveData : LiveData<List<Article>> = repo.readAllData

}