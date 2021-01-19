package com.matej.factorynews.ui.news.newsList

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.matej.factorynews.FactoryNews
import com.matej.factorynews.R
import com.matej.factorynews.common.ERROR_BUTTON_TITLE
import com.matej.factorynews.common.ERROR_MESSAGE
import com.matej.factorynews.common.ERROR_TITLE
import com.matej.factorynews.common.showFragment
import com.matej.factorynews.db.NewsDatabase
import com.matej.factorynews.model.NewsDb
import com.matej.factorynews.networking.BackendFactory
import com.matej.factorynews.persistance.FragmentDataRepositoryImpl
import com.matej.factorynews.presentation.NewsListPresenter
import com.matej.factorynews.ui.NewsActivity
import com.matej.factorynews.ui.adapter.NewsListAdapter
import com.matej.factorynews.ui.base.BaseFragment
import com.matej.factorynews.ui.news.newsDetails.NewsDetailsFragment
import kotlinx.android.synthetic.main.fragment_news_list.*

class NewsListFragment : BaseFragment(), NewsListContract.View {

    private val adapter by lazy { NewsListAdapter(::onArticleClicked) }
    private val presenter: NewsListContract.Presenter by lazy {
        NewsListPresenter(
            NewsDatabase.getInstance(FactoryNews.instance),
            BackendFactory.getNewsInteractor(),
            FragmentDataRepositoryImpl()
        )
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_news_list

    override fun setupUi() {
        toolbarTitle.setText(R.string.app_name)
        presenter.setView(this)
        getNews()
        setRecyclerView()
    }

    private fun getNews(){
        presenter.getNews()
        progressBar.visibility = View.VISIBLE
    }

    private fun setRecyclerView() {
        newsListRecyclerView.layoutManager = LinearLayoutManager(context)
        newsListRecyclerView.adapter = adapter
    }

    private fun onArticleClicked(articleIndex: Int){
        presenter.saveArticleIndex(articleIndex)
        activity?.showFragment(R.id.fragmentContainer, NewsDetailsFragment.newInstance(), true)
    }

    override fun onGetNewsSuccessful(news: List<NewsDb>) {
        adapter.setData(news)
        presenter.removeCurrentNews()
        presenter.saveNews(news)
        presenter.saveNewsToRepository(news)
        progressBar.visibility = View.GONE
    }

    override fun onGetNewsFailed() {
        progressBar.visibility = View.GONE
        val builder: AlertDialog.Builder? = activity?.let { AlertDialog.Builder(it) }
        builder?.setMessage(ERROR_MESSAGE)?.setTitle(ERROR_TITLE)
        builder?.apply { setPositiveButton(ERROR_BUTTON_TITLE, { dialog, id ->}) }
        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }

    companion object {
        fun newInstance(): Fragment = NewsListFragment()
    }
}
