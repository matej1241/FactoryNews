package com.matej.factorynews.ui.news.newsDetails

import com.matej.factorynews.model.NewsDb

interface NewsDetailsContract {

    interface View{}

    interface Presenter{
        fun setView(view: View)
        fun getNewsList() : List<NewsDb>
        fun getSelectedIndex() : Int
    }
}