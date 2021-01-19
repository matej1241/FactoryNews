package com.matej.factorynews.persistance

import com.matej.factorynews.model.NewsDb

class FragmentDataRepositoryImpl: FragmentDataRepository{

    companion object{
        private var selectedArticleIndex = 0
        private var newsList: MutableList<NewsDb> = mutableListOf()
    }

    override fun saveNewsList(news: List<NewsDb>){
        newsList.clear()
        newsList = news.toMutableList()
    }

    override fun saveSelectedIndex(index: Int) {
        selectedArticleIndex = index
    }

    override fun getNewsList(): List<NewsDb> = newsList

    override fun getSelectedIndex(): Int = selectedArticleIndex
}