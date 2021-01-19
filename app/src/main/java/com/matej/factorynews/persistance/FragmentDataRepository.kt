package com.matej.factorynews.persistance

import com.matej.factorynews.model.NewsDb

interface FragmentDataRepository {

    fun saveNewsList(news: List<NewsDb>)
    fun getNewsList(): List<NewsDb>
    fun saveSelectedIndex(index: Int)
    fun getSelectedIndex(): Int
}