package com.matej.factorynews.networking.interactors

import com.matej.factorynews.model.NewsResponse
import com.matej.factorynews.networking.NewsApiService
import retrofit2.Callback

class NewsInteractorImpl(private val newsApiService: NewsApiService): NewsInteractor {

    override fun getNews(newsResponseCallback: Callback<NewsResponse>) {
        newsApiService.getNews().enqueue(newsResponseCallback)
    }
}