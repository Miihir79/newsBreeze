package com.mihir.newsbreeze.ui.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.mihir.newsbreeze.R
import com.mihir.newsbreeze.USER_ACTION_READ_ITEM
import com.mihir.newsbreeze.USER_ACTION_SAVE_ITEM
import com.mihir.newsbreeze.databinding.ActivityLandingPageBinding
import com.mihir.newsbreeze.helper.CheckInternet
import com.mihir.newsbreeze.ui.adapter.NewsListAdapter
import com.mihir.newsbreeze.viewmodel.ViewModel

class LandingPageActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLandingPageBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[ViewModel::class.java] }
    private val networkConnectivity by lazy { CheckInternet(application) }

    private lateinit var adapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_500)
        supportActionBar?.hide()
        setContentView(binding.root)

        adapter = NewsListAdapter { userAction, article ->
            when (userAction) {
                USER_ACTION_SAVE_ITEM -> {
                    viewModel.addNewsToSaved(article)
                    Toast.makeText(this, "Added", Toast.LENGTH_LONG).show()
                }
                USER_ACTION_READ_ITEM -> {
                    val intent = Intent(this, ReadActivity::class.java)
                    intent.putExtra("Article", article)
                    startActivity(intent)
                }
            }
        }

        binding.recyclerLanding.adapter = adapter
        viewModel.newsLiveData.observe(this) {
            adapter.articleList = it.articles
        }

        binding.recyclerLanding.setHasFixedSize(true)

        binding.imgBtnSave.setOnClickListener {
            val intent = Intent(this, SavedNewsActivity::class.java)
            startActivity(intent)
        }

        binding.search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String): Boolean {
                adapter.filter = p0
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

}