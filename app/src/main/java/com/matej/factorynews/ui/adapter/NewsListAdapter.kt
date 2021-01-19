package com.matej.factorynews.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matej.factorynews.FactoryNews
import com.matej.factorynews.R
import com.matej.factorynews.common.onArticleClickedListener
import com.matej.factorynews.model.Articles
import com.matej.factorynews.model.NewsDb
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news_article.view.*

class NewsListAdapter(
    private val articleListener: onArticleClickedListener
): RecyclerView.Adapter<NewsDataHolder>() {

    private val news: MutableList<NewsDb> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsDataHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_article, parent, false)
        return NewsDataHolder(view)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: NewsDataHolder, position: Int) {
        holder.bindData(news[position], articleListener, position)
    }

    fun setData(data: List<NewsDb>) {
        this.news.clear()
        this.news.addAll(data)
        notifyDataSetChanged()
    }
}

class NewsDataHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bindData(data: NewsDb, articleListener: onArticleClickedListener, articleIndex: Int){
        itemView.setOnClickListener { articleListener(articleIndex) }
        itemView.articleTitle.text = data.title
        Picasso.with(FactoryNews.instance)
            .load(data.urlToImage)
            .fit()
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(itemView.articleImage)
    }
}
