package com.mihir.newsbreeze.ui.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.mihir.newsbreeze.R
import com.mihir.newsbreeze.data.model.Article
import com.mihir.newsbreeze.databinding.ActivitySavedNewsBinding
import com.mihir.newsbreeze.ui.adapter.SavedNewsListAdapter
import com.mihir.newsbreeze.viewmodel.SavedNewsViewModel
import java.util.*
import kotlin.collections.ArrayList

class SavedNewsActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySavedNewsBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[SavedNewsViewModel::class.java] }

    private lateinit var newsList: ArrayList<Article>
    private val adapter by lazy {  SavedNewsListAdapter(newsList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_500)
        supportActionBar?.hide()
        setContentView(binding.root)

        viewModel.allSavedNewsLiveData.observe(this){
            if (it.isEmpty()){
                binding.txtVNoNews.visibility = View.VISIBLE
            }else{
                binding.txtVNoNews.visibility = View.GONE
            }
            newsList = it as ArrayList<Article>
            binding.recyclerSaved.adapter = adapter
        }
        binding.imgVBack.setOnClickListener {
            finish()
        }

        binding.search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String): Boolean {
                filter(p0)
                return true
            }
        })
    }

    private fun filter(text: String) {
        val filtered = ArrayList<Article>()

        for (item in newsList) {
            if (item.title?.lowercase()?.contains(text.lowercase(Locale.getDefault())) == true) {
                filtered.add(item)
            }
        }
        if (filtered.isEmpty().not()) {
            adapter.filterList(filtered)
        }
    }
}