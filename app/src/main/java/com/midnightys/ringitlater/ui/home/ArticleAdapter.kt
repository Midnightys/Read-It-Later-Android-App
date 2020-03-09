package com.midnightys.ringitlater.ui.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.midnightys.ringitlater.databinding.ItemArticlesBinding
import com.midnightys.ringitlater.model.ArticleInfo

/**
 * Created by Kort on 2020/3/8.
 */
typealias OnArticleClickListener = (ArticleInfo) -> Unit

class ArticleAdapter() :
    ListAdapter<ArticleInfo, ArticleAdapter.ArticleViewHolder>(ArticleInfo.diff) {

    var onArticleClickListener: OnArticleClickListener? = null
    fun onArticleClick(onArticleClickListener: OnArticleClickListener) {
        this.onArticleClickListener = onArticleClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArticlesBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ArticleViewHolder(private val binding: ItemArticlesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(articleInfo: ArticleInfo) {
            binding.apply {
                title.text = articleInfo.title
                description.text = articleInfo.description
                image.load(articleInfo.imageUrl)
            }

            itemView.setOnClickListener { onArticleClickListener?.invoke(articleInfo) }
        }
    }
}