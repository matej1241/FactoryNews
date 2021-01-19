package com.matej.factorynews.networking.interactors

import com.matej.factorynews.model.NewsResponse
import retrofit2.Callback

interface NewsInteractor {

    fun getNews(newsResponseCallback: Callback<NewsResponse>)
}