package com.mihir.newsbreeze.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mihir.newsbreeze.data.model.Article
import com.mihir.newsbreeze.databinding.ItemNewsSavedBinding
import com.mihir.newsbreeze.ui.screen.ReadActivity

class SavedNewsListAdapter(private var items : List<Article>) : RecyclerView.Adapter<SavedNewsListAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(binding: ItemNewsSavedBinding) : RecyclerView.ViewHolder(binding.root) {
        val titleView: TextView = binding.txtVTitle
        val image: ImageView = binding.image
        val date: TextView = binding.txtVDateName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(ItemNewsSavedBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    fun filterList(filterList: ArrayList<Article>) {
        items = filterList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.date.text = "${currentItem.publishedAt} . ${currentItem.author}"
        Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.image)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ReadActivity::class.java)
            intent.putExtra("Article",items[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}