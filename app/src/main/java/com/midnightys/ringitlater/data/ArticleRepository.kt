package com.midnightys.ringitlater.data

import com.google.firebase.firestore.DocumentReference
import com.midnightys.ringitlater.model.Article
import com.midnightys.ringitlater.model.ArticleInfo
import com.midnightys.status.*
import com.midnightys.toolboxapp.addOnTaskStatusChangeFlow
import com.midnightys.toolboxapp.toFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Created by Kort on 2020/3/8.
 */
interface ArticleRepositoryProvider {
    fun createArticle(article: Article): Flow<Status<Unit>>
    fun getAllArticles(): Flow<Status<List<Article>>>
}

class ArticleRepository(private val userRepository: UserRepositoryProvider) :
    ArticleRepositoryProvider {
    val user by lazy { userRepository.getUserOnce() }

    override fun createArticle(article: Article): Flow<Status<Unit>> =
        userRepository.getUserDocument().map {
            it.flatMap { userDocument ->
                val document = userDocument.collection(ARTICLE_COLLECTION).document()
                article.id = document.id
                document.set(article)
                    .addOnTaskStatusChangeFlow()
                    .statusSingle()
            }
        }

    override fun getAllArticles(): Flow<Status<List<Article>>> =
        userRepository.getUserDocument().flatMapConcat {
            if (it is Success) it.result.collection(ARTICLE_COLLECTION).toFlow<Article>()
            else flow { emit(it as Status<List<Article>>) }
        }

    companion object {
        const val ARTICLE_COLLECTION = "articles"
    }
}