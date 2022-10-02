package com.mihir.newsbreeze.data.model

import androidx.annotation.Keep
import com.mihir.newsbreeze.data.model.Article

@Keep
data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)