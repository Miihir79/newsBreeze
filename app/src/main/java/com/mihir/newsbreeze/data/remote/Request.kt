package com.mihir.newsbreeze.data.remote

import com.mihir.newsbreeze.data.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Request {

    @GET("v2/top-headlines?country=in")
    suspend fun getNews(@Query("apiKey")apiKey:String):Response<News>

    // To fetch from a different topic, maybe in future?
    @GET("v2/top-headlines?country=in")
    suspend fun getSpecificNews(@Query("category")type:String,@Query("apiKey")apiKey:String):Response<News>
}