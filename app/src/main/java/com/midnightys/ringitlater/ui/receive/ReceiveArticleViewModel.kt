package com.midnightys.ringitlater.ui.receive

import androidx.lifecycle.ViewModel
import com.midnightys.ringitlater.data.ArticleRepositoryProvider
import com.midnightys.ringitlater.data.UrlIsBlankException
import com.midnightys.ringitlater.model.Article
import com.midnightys.status.Failure
import com.midnightys.status.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Kort on 2020/3/8.
 */
class ReceiveArticleViewModel(private val articleRepository: ArticleRepositoryProvider) :
    ViewModel() {
    fun createArticle(links: String): Flow<Status<Unit>> =
        links.takeIf { it.isNotBlank() }?.let {
            val article = Article(url = links)
            articleRepository.createArticle(article)
        } ?: flow {
            Failure(UrlIsBlankException())
        }
}