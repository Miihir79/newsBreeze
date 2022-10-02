package com.mihir.newsbreeze.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mihir.newsbreeze.data.model.Article
import com.mihir.newsbreeze.databinding.ItemNewsBinding
import com.mihir.newsbreeze.ui.screen.ReadActivity
import com.mihir.newsbreeze.viewmodel.ViewModel

class NewsListAdapter(private var items : ArrayList<Article>, private val viewModel: ViewModel) : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    inner class NewsViewHolder (binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root){
        val titleView: TextView = binding.txtVTitle
        val image : ImageView = binding.image
        val body: TextView = binding.txtVBody
        val date : TextView = binding.txtVDate
        val btnSave : Button = binding.btnSave
        val btnRead : Button = binding.btnRead
        val btnIndicatorNotSaved : ImageButton = binding.imgBtnNotSaved
        val btnIndicatorSaved : ImageButton = binding.imgBtnSaved
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    fun filterList(filterNewsList: ArrayList<Article>) {
        items = filterNewsList
        // not the best practice to use it, but here we are :(
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.body.text = currentItem.content
        holder.date.text = currentItem.publishedAt  // to be converted in required time format
        Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.image)

        holder.btnRead.setOnClickListener {

            val intent = Intent(holder.itemView.context,ReadActivity::class.java)
            intent.putExtra("Article", items[position])
            holder.itemView.context.startActivity(intent)

        }

        holder.btnSave.setOnClickListener {

            viewModel.addNewsToSaved(items[position])

            holder.btnIndicatorSaved.visibility = View.VISIBLE
            holder.btnIndicatorNotSaved.visibility = View.GONE

            Toast.makeText(holder.itemView.context,"Added", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}