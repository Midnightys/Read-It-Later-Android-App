package com.midnightys.ringitlater.ui.receive

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.midnightys.ringitlater.R
import com.midnightys.ringitlater.ui.main.MainActivity
import com.midnightys.ringitlater.util.pullLinks
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


class ReceiveArticleActivity : AppCompatActivity() {
    val viewModel: ReceiveArticleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recived_article)
        if (intent?.action == Intent.ACTION_SEND) {
            if ("text/plain" == intent.type) {
                intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                    handleSendText(it)
                }
            }
        }
    }

    private fun handleSendText(text: String) {
        viewModel.createArticle(pullLinks(text)).onEach {
            startActivity(Intent(this, MainActivity::class.java))
        }.launchIn(lifecycleScope)
    }
}