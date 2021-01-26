package com.matej.factorynews.ui.news.newsDetails

import androidx.fragment.app.Fragment
import com.matej.factorynews.FactoryNews
import com.matej.factorynews.R
import com.matej.factorynews.db.NewsDatabase
import com.matej.factorynews.model.NewsDb
import com.matej.factorynews.networking.BackendFactory
import com.matej.factorynews.persistance.FragmentDataRepositoryImpl
import com.matej.factorynews.presentation.NewsDetailsPresenter
import com.matej.factorynews.presentation.NewsListPresenter
import com.matej.factorynews.ui.adapter.NewsViewPagerAdapter
import com.matej.factorynews.ui.base.BaseFragment
import com.matej.factorynews.ui.news.newsList.NewsListContract
import kotlinx.android.synthetic.main.fragment_news_details.*


class NewsDetailsFragment : BaseFragment(), NewsDetailsContract.View {

    private val adapter by lazy { NewsViewPagerAdapter(::onBackButtonClicked) }
    private val presenter: NewsDetailsContract.Presenter by lazy { NewsDetailsPresenter(FragmentDataRepositoryImpl()) }

    override fun getLayoutResourceId(): Int = R.layout.fragment_news_details

    override fun setupUi() {
        presenter.setView(this)
        presenter.getData()
    }

    private fun setViewPager(news: List<NewsDb>, selectedIndex: Int) {
        newsViewPager.adapter = adapter
        adapter.setData(news)
        newsViewPager.post{ newsViewPager.setCurrentItem(selectedIndex, false) }
    }

    private fun onBackButtonClicked() {
        fragmentManager?.popBackStack()
    }

    override fun onDataRetrieved(news: List<NewsDb>, selectedIndex: Int) {
        setViewPager(news, selectedIndex)
    }

    companion object {
        fun newInstance(): Fragment = NewsDetailsFragment()
    }
}
