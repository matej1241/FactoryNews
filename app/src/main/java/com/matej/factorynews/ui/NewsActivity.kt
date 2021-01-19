package com.matej.factorynews.ui

import android.app.AlertDialog
import android.content.DialogInterface
import com.matej.factorynews.FactoryNews
import com.matej.factorynews.R
import com.matej.factorynews.common.showFragment
import com.matej.factorynews.ui.base.BaseActivity
import com.matej.factorynews.ui.news.newsList.NewsListFragment

class NewsActivity : BaseActivity() {

    override fun getLayoutResourceId(): Int = R.layout.activity_news

    override fun setupUi() {
        showFragment(R.id.fragmentContainer, NewsListFragment.newInstance())
    }
}
