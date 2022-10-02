package com.mihir.newsbreeze.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mihir.newsbreeze.BuildConfig
import com.mihir.newsbreeze.data.local.NewsDatabase
import com.mihir.newsbreeze.data.model.Article
import com.mihir.newsbreeze.data.model.News
import com.mihir.newsbreeze.data.remote.Request
import com.mihir.newsbreeze.data.repo.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewModel(application: Application):AndroidViewModel(application) {
    private val baseUrl ="https://newsapi.org/"
    private val retrofit by lazy { Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()}
    private val service by lazy { retrofit.create(Request::class.java)}
    private val dao by lazy { NewsDatabase.getDatabase(application).NewsDao() }
    private val repo by lazy { NewsRepo(dao) }
    val newsLiveData: MutableLiveData<News> = MutableLiveData()

    init {
        getNews(null)
    }

    //function made keeping future api calls which may have a search term
    fun getNews(term:String?){
        viewModelScope.launch(Dispatchers.IO) {
            if (term.isNullOrEmpty()){
                try {
                    // API key hidden in local.properties and accessed through buildconfig
                    val response = service.getNews(BuildConfig.API_KEY)
                    if (response.isSuccessful){
                        newsLiveData.postValue(response.body())
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }

            }else{
                try {
                    val response = service.getSpecificNews(term, BuildConfig.API_KEY)
                    if (response.isSuccessful){
                        newsLiveData.postValue(response.body())
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    fun addNewsToSaved(news:Article){
        viewModelScope.launch(Dispatchers.IO){
            repo.addNews(news)
        }
    }
}