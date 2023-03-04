package com.mihir.newsbreeze.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mihir.newsbreeze.USER_ACTION_READ_ITEM
import com.mihir.newsbreeze.USER_ACTION_SAVE_ITEM
import com.mihir.newsbreeze.data.model.Article
import com.mihir.newsbreeze.databinding.ItemNewsBinding

class NewsListAdapter(
    val onItemClicked: ((userAction: String, article: Article) -> Unit)
) : ListAdapter<Article,NewsListAdapter.NewsViewHolder>(ItemCallback) {

    object ItemCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem == newItem
    }

    inner class NewsViewHolder(binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        val titleView: TextView = binding.txtVTitle
        val image: ImageView = binding.image
        val body: TextView = binding.txtVBody
        val date: TextView = binding.txtVDate
        val btnSave: Button = binding.btnSave
        val btnRead: Button = binding.btnRead
        val btnIndicatorNotSaved: ImageButton = binding.imgBtnNotSaved
        val btnIndicatorSaved: ImageButton = binding.imgBtnSaved
    }

    var articleList: List<Article> = emptyList()
        set(value) {
            field = value
            onListOrFilterChange()
        }

    private fun onListOrFilterChange() {
        if (filter.length < 2) {
            submitList(articleList)
            return
        }
        val pattern = filter.toString().lowercase().trim()
        val filteredList = articleList.filter { pattern in (it.title ?: "".lowercase()) }
        submitList(filteredList)
    }

    var filter: CharSequence = ""
        set(value) {
            field = value
            onListOrFilterChange()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = currentList[position]
        holder.titleView.text = currentItem.title
        holder.body.text = currentItem.content
        holder.date.text = currentItem.publishedAt  // to be converted in required time format
        Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.image)

        holder.btnRead.setOnClickListener {
            onItemClicked(USER_ACTION_READ_ITEM, currentItem)
        }

        holder.btnSave.setOnClickListener {
            onItemClicked(USER_ACTION_SAVE_ITEM, currentItem)
            holder.btnIndicatorSaved.visibility = View.VISIBLE
            holder.btnIndicatorNotSaved.visibility = View.GONE
        }
    }

}