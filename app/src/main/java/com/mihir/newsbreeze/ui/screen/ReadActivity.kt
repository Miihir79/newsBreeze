package com.mihir.newsbreeze.ui.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mihir.newsbreeze.R
import com.mihir.newsbreeze.data.model.Article
import com.mihir.newsbreeze.databinding.ActivityReadBinding
import com.mihir.newsbreeze.viewmodel.ReadViewModel

class ReadActivity : AppCompatActivity() {

    private val binding by lazy { ActivityReadBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[ReadViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_500)
        supportActionBar?.hide()
        setContentView(binding.root)

        val article = intent.getParcelableExtra<Article>("Article")

        binding.imgVBack.setOnClickListener{
            finish()
        }

        binding.article = article

        Glide.with(binding.imageView).load(article?.urlToImage).into(binding.imageView)

        binding.imgVSave.setOnClickListener {
            if (article != null) {
                viewModel.addNewsToSaved(article)
                Toast.makeText(this,"Added", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnReadSave.setOnClickListener {
            if (article != null) {
                viewModel.addNewsToSaved(article)
                Toast.makeText(this,"Added",Toast.LENGTH_LONG).show()
            }
        }
    }
}