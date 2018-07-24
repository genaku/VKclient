package com.genaku.vkclient.activity

import android.graphics.drawable.Drawable
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.genaku.domain.interfaces.interactors.INewsInteractor
import com.genaku.vkclient.GlideApp
import com.genaku.vkclient.GlideRequest
import com.genaku.vkclient.R
import com.genaku.vkclient.adapters.NewsAdapter
import com.genaku.vkclient.getViewModel
import com.genaku.vkclient.navigation.NewsNavigator
import com.genaku.vkclient.paging.ActivityPreloadModelProvider
import com.genaku.vkclient.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_news.*
import org.jetbrains.anko.toast

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class NewsActivity : NavigationalActivity(), SwipeRefreshLayout.OnRefreshListener {

    private val mNewsNavigator = NewsNavigator(mNavigator)
    private lateinit var mInteractor: INewsInteractor

    private lateinit var mGlideRequest: GlideRequest<Drawable>
    private val mPreloadModelProvider = ActivityPreloadModelProvider(this)
    private val mPagingAdapter by lazy { NewsAdapter(mGlideRequest) { postData -> mNewsNavigator.openPost(postData) } }

    private lateinit var refreshNews: () -> Unit

    override val layoutResId: Int = R.layout.activity_news

    override fun setupView() {
        getViewModel {
            NewsViewModel(mRepository)
        }.apply {
            backToLoginEvent.observeWith {
                mNewsNavigator.backToLogin()
            }
            newsLiveData.observeWith {
                val urls = ArrayList<String>()
                it.forEach { item ->
                    urls.add(item.authorAvatarUrl)
                    if (item.pictures.size > 0) {
                        urls.add(item.pictures[0])
                    }
                }
                mPreloadModelProvider.setUrlList(urls)
                mPagingAdapter.submitList(it)
                setRefreshingState(false)
            }
            errorEvent.observeWith { toast(R.string.error) }
            refreshingState.observeWith(::setRefreshingState)
            refreshNews = { newsLiveData.value?.dataSource?.invalidate() }
            mInteractor = interactor
        }
    }

    override fun setupUI() {
        setupGlide()
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = mPagingAdapter
        refresher.setColorSchemeResources(R.color.colorPrimary)
        refresher.setOnRefreshListener(this)
    }

    private fun setupGlide() {
        mGlideRequest = GlideApp.with(this).asDrawable().fitCenter()
        val preLoader = RecyclerViewPreloader<String>(
                GlideApp.with(this),
                mPreloadModelProvider,
                ViewPreloadSizeProvider<String>(),
                PRELOAD_AHEAD_ITEMS)
        rvNews.addOnScrollListener(preLoader)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.news_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_logout -> {
            mInteractor.logout()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onRefresh() {
        refreshNews()
    }

    private fun setRefreshingState(value: Boolean) {
        refresher.isRefreshing = value
    }

    companion object {
        private const val PRELOAD_AHEAD_ITEMS = 9
    }

}