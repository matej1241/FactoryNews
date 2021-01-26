package com.matej.factorynews.ui.news.newsDetails

import com.matej.factorynews.model.NewsDb

interface NewsDetailsContract {

    interface View{
        fun onDataRetrieved(news: List<NewsDb>, selectedIndex: Int)
    }

    interface Presenter{
        fun setView(view: View)
        fun getData()
        fun getNewsList() : List<NewsDb>
        fun getSelectedIndex() : Int
    }
}