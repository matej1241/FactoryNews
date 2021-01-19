package com.matej.factorynews.networking

import com.matej.factorynews.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsApiService {

    @GET("v1/articles?source=bbc-news&sortBy=top&apiKey=6946d0c07a1c4555a4186bfcade76398")
    fun getNews(): Call<NewsResponse>
}