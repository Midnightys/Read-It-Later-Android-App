package com.midnightys.ringitlater.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.midnightys.common.onEachInViewLifeScope
import com.midnightys.ringitlater.R
import com.midnightys.ringitlater.databinding.HomeFragmentBinding
import com.midnightys.status.Success
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    lateinit var binding: HomeFragmentBinding
    private val viewModel: HomeViewModel by viewModel()
    val articleAdapter by lazy { ArticleAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindViewModel()
        setupArticleAdapter()
    }

    private fun bindViewModel() {
        viewModel.articleInfoList.onEachInViewLifeScope(this) {
            if (it is Success) articleAdapter.submitList(it.result)
        }
        viewModel.userTitle.onEachInViewLifeScope(this) {
            if (it is Success) binding.title.setText(
                requireContext().getString(R.string.greet_user, it.result)
            )
        }
    }

    private fun setupArticleAdapter() {
        binding.articleList.adapter = articleAdapter.apply {
            onArticleClick {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    setData(Uri.parse(it.url))
                    startActivity(this)
                }
            }
        }
    }
}