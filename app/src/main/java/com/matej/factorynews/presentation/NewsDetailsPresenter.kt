package com.matej.factorynews.presentation

import com.matej.factorynews.model.NewsDb
import com.matej.factorynews.persistance.FragmentDataRepository
import com.matej.factorynews.ui.news.newsDetails.NewsDetailsContract

class NewsDetailsPresenter(
    private val fragmentDataRepository: FragmentDataRepository
): NewsDetailsContract.Presenter {

    private lateinit var view: NewsDetailsContract.View

    override fun setView(view: NewsDetailsContract.View) {
        this.view = view
    }

    override fun getData() = onDataRetrieved(getNewsList(), getSelectedIndex())

    override fun getNewsList(): List<NewsDb> = fragmentDataRepository.getNewsList()

    override fun getSelectedIndex(): Int = fragmentDataRepository.getSelectedIndex()

    private fun onDataRetrieved(news: List<NewsDb>, selectedIndex: Int) = view.onDataRetrieved(news, selectedIndex)
}