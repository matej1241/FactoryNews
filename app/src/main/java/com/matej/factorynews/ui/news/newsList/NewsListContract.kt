package com.matej.factorynews.ui.news.newsList

import com.matej.factorynews.model.Articles
import com.matej.factorynews.model.NewsDb

interface NewsListContract {

    interface View{
        fun onGetNewsSuccessful(news: List<NewsDb>)
        fun onGetNewsFailed()
    }

    interface Presenter{
        fun setView(view: View)
        fun getNews()
        fun saveNews(news: List<NewsDb>)
        fun getDbNews(): List<NewsDb>
        fun removeCurrentNews()
        fun saveArticleIndex(index: Int)
        fun getArticleIndex(): Int
        fun saveNewsToRepository(news: List<NewsDb>)
    }
}