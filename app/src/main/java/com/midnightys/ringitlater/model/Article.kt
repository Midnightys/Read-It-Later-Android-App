package com.midnightys.ringitlater.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import com.midnightys.ringitlater.util.*
import kotlinx.android.parcel.Parcelize
import org.jsoup.Jsoup
import org.w3c.dom.Document
import timber.log.Timber

/**
 * Created by Kort on 2020/3/8.
 */
@Parcelize
data class Article(
    var id: String = "",
    var url: String = "",
    var createdTimestamp: Timestamp = Timestamp.now()
) : Parcelable {
    fun toArticleInfo(): ArticleInfo? {
        Timber.d("url: $url")
        return url.takeIf { it.isNotBlank() }?.let {
            Jsoup.connect(url).get().run {
                ArticleInfo(
                    id,
                    url,
                    title,
                    description,
                    image,
                    icon,
                    siteName
                )
            }
        }
    }
}