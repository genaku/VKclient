package com.genaku.domain

import com.genaku.domain.interfaces.IRepository
import com.genaku.domain.interfaces.interactors.IGetNewsInteractor
import com.genaku.domain.interfaces.presenters.IGetNewsPresenter
import com.genaku.domain.model.NewsFeedData

class GetNewsUseCase(
        private val repository: IRepository,
        private val presenter: IGetNewsPresenter
) : IGetNewsInteractor {

    override fun getNewsFeed(startFrom: String, count: Int): NewsFeedData {
        return try {
            repository.getNews(startFrom, count)
        } catch (e: Exception) {
            showError(e)
            NewsFeedData(prevKey = "", nextKey = startFrom, items = ArrayList())
        }
    }

    private fun showError(e: Exception) {
        e.printStackTrace()
        presenter.showError()
    }

}