package com.mihir.newsbreeze.ui.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.mihir.newsbreeze.R
import com.mihir.newsbreeze.data.model.Article
import com.mihir.newsbreeze.databinding.ActivityLandingPageBinding
import com.mihir.newsbreeze.helper.CheckInternet
import com.mihir.newsbreeze.ui.adapter.NewsListAdapter
import com.mihir.newsbreeze.viewmodel.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class LandingPageActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLandingPageBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[ViewModel::class.java] }
    private val networkConnectivity by lazy { CheckInternet(application) }

    private val adapter by lazy {  NewsListAdapter(newsList,viewModel)}

    private lateinit var newsList: ArrayList<Article>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_500)
        supportActionBar?.hide()
        setContentView(binding.root)

        viewModel.newsLiveData.observe(this){
            newsList = it.articles as ArrayList<Article>
            binding.recyclerLanding.adapter = adapter
        }

        binding.recyclerLanding.setHasFixedSize(true)

        binding.imgBtnSave.setOnClickListener {
            val intent = Intent(this,SavedNewsActivity::class.java)
            startActivity(intent)
        }

        binding.search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String): Boolean {
                filter(p0)
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }
        })

        //to make constant listener for internet check
        val snackBarNoNetwork = Snackbar.make(
            findViewById(android.R.id.content),
            "No Internet",
            Snackbar.LENGTH_INDEFINITE
        )

        networkConnectivity.observe(this) { isConnected ->
            when (isConnected) {
                true -> {
                    snackBarNoNetwork.dismiss()
                }

                false -> {
                    snackBarNoNetwork.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
                    snackBarNoNetwork.setAction("Dismiss") {
                        snackBarNoNetwork.dismiss()
                    }
                        .show()
                }
            }
        }

    }

    private fun filter(text: String) {
        val filteredNews = ArrayList<Article>()

        for (item in newsList) {
            if (item.title?.lowercase()?.contains(text.lowercase(Locale.getDefault())) == true) {
                filteredNews.add(item)
            }
        }
        if (filteredNews.isEmpty().not()) {
            adapter.filterList(filteredNews)
        }
    }
}