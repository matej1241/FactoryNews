package com.matej.factorynews.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matej.factorynews.FactoryNews
import com.matej.factorynews.R
import com.matej.factorynews.common.onBackButtonClicked
import com.matej.factorynews.model.NewsDb
import com.matej.factorynews.ui.NewsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_pager_article.view.*

class NewsViewPagerAdapter(
    private val backButtonClicked: onBackButtonClicked
): RecyclerView.Adapter<NewsViewPagerViewHolder>() {

    private val news: MutableList<NewsDb> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager_article, parent, false)
        return NewsViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: NewsViewPagerViewHolder, position: Int) {
        holder.bindData(news[position], backButtonClicked)
    }

    fun setData(data: List<NewsDb>) {
        this.news.clear()
        this.news.addAll(data)
        notifyDataSetChanged()
    }
}

class NewsViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bindData(data: NewsDb, backButtonClicked: onBackButtonClicked){
        itemView.backButton.setOnClickListener { backButtonClicked() }
        itemView.titleText.text = data.title
        itemView.articleDescriptionText.text = data.description
        itemView.toolbarTitle.text = data.title
        Picasso.with(FactoryNews.instance)
            .load(data.urlToImage)
            .fit()
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(itemView.articleImage)
    }
}

