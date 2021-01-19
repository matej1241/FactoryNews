package com.matej.factorynews.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.matej.factorynews.model.Articles
import com.matej.factorynews.model.NewsDb

@Dao
interface NewsDao {

    @Insert
    fun insertNews(news: List<NewsDb>)

    @Query("SELECT * FROM News")
    fun getNews(): List<NewsDb>

    @Query("DELETE FROM News")
    fun removeNews()
}