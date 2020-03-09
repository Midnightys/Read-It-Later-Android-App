package com.midnightys.ringitlater.ui.home.domain

import com.midnightys.ringitlater.data.ArticleRepositoryProvider
import com.midnightys.ringitlater.model.Article
import com.midnightys.ringitlater.model.ArticleInfo
import com.midnightys.status.Status
import com.midnightys.status.mapStatus
import com.midnightys.usecase.StatusUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

/**
 * Created by Kort on 2020/3/8.
 */
class GetAllArticleUseCase(private val articleRepository: ArticleRepositoryProvider) :
    StatusUseCase<CoroutineScope, List<ArticleInfo>>() {
    override fun execute(parameter: CoroutineScope): Flow<Status<List<ArticleInfo>>> =
        articleRepository.getAllArticles().mapStatus { list ->
            Timber.d("getArticles : $list")
            parameter.async(Dispatchers.IO) {
                list.mapNotNull(Article::toArticleInfo)
            }.await()
        }
}