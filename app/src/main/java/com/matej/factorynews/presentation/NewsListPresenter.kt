package com.matej.factorynews.presentation

import android.util.Log
import com.matej.factorynews.db.NewsDatabase
import com.matej.factorynews.model.Articles
import com.matej.factorynews.model.NewsDb
import com.matej.factorynews.model.NewsResponse
import com.matej.factorynews.networking.interactors.NewsInteractor
import com.matej.factorynews.persistance.FragmentDataRepository
import com.matej.factorynews.persistance.FragmentDataRepositoryImpl
import com.matej.factorynews.ui.news.newsList.NewsListContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListPresenter(
    private val appDb: NewsDatabase,
    private val newsInteractor: NewsInteractor,
    private val newsRepository: FragmentDataRepository
): NewsListContract.Presenter {

    private lateinit var view: NewsListContract.View

    override fun setView(view: NewsListContract.View) {
        this.view = view
    }

    override fun getNews(){
        val dbNews = getDbNews()
        when{
            dbNews.isEmpty() -> newsInteractor.getNews(getNewsCallback())
            getTime() - dbNews[0].timeStamp < 300000 -> onResponseSuccessful(dbNews)
            else -> newsInteractor.getNews(getNewsCallback())
        }
    }

    override fun getDbNews(): List<NewsDb> = appDb.newsDao().getNews()

    override fun saveNews(news: List<NewsDb>) = appDb.newsDao().insertNews(news)

    override fun removeCurrentNews() = appDb.newsDao().removeNews()

    override fun saveArticleIndex(index: Int) = newsRepository.saveSelectedIndex(index)

    override fun getArticleIndex(): Int = newsRepository.getSelectedIndex()

    override fun saveNewsToRepository(news: List<NewsDb>) = newsRepository.saveNewsList(news)

    private fun getNewsCallback(): Callback<NewsResponse> = object : Callback<NewsResponse> {
        override fun onFailure(call: Call<NewsResponse>?, t: Throwable?) {
                onResponseFailure()
        }
        override fun onResponse(call: Call<NewsResponse>?, response: Response<NewsResponse>) {
            if (response.isSuccessful) { onResponseSuccessful(extractNewsData(response.body()!!.articles)) }
        }
    }

    private fun extractNewsData(news: List<Articles>): List<NewsDb>{
        val newsList: MutableList<NewsDb> = mutableListOf()
        for (article in news){
            newsList.add(
                NewsDb(
                    title = article.title,
                    urlToImage = article.urlToImage,
                    description = article.description,
                    timeStamp = System.currentTimeMillis()
                )
            )
        }
        return newsList
    }

    private fun getTime(): Long = System.currentTimeMillis()

    private fun onResponseSuccessful(news: List<NewsDb>) = view.onGetNewsSuccessful(news)

    private fun onResponseFailure() = view.onGetNewsFailed()
}