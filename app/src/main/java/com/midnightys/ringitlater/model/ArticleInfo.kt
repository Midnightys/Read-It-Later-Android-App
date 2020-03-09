package com.midnightys.ringitlater.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

/**
 * Created by Kort on 2020/3/8.
 */
@Parcelize
data class ArticleInfo(
    var id: String,
    val url: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val iconUrl: String,
    val siteName: String
) : Parcelable {
    companion object {
        val diff = object : DiffUtil.ItemCallback<ArticleInfo>() {
            override fun areItemsTheSame(oldItem: ArticleInfo, newItem: ArticleInfo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ArticleInfo, newItem: ArticleInfo): Boolean =
                oldItem == newItem
        }
    }
}