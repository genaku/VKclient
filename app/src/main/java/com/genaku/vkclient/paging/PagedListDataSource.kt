package com.genaku.vkclient.paging

import android.arch.paging.PageKeyedDataSource
import com.genaku.domain.interfaces.interactors.IGetNewsInteractor
import com.genaku.domain.model.PostData
import com.genaku.vkclient.uimodel.UIPostData
import com.genaku.vkclient.uimodel.toUI

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class PagedListDataSource(
        private val interactor: IGetNewsInteractor
) : PageKeyedDataSource<String, UIPostData>() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, UIPostData>) {
        val response = interactor.getNewsFeed("", params.requestedLoadSize)
        callback.onResult(prepareUIPostData(response.items), null, response.nextKey)
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, UIPostData>) {
        val response = interactor.getNewsFeed(params.key, params.requestedLoadSize)
        callback.onResult(prepareUIPostData(response.items), response.nextKey)
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, UIPostData>) {
    }

    private fun prepareUIPostData(items: ArrayList<PostData>): ArrayList<UIPostData> {
        return items.mapTo(ArrayList()) { it -> it.toUI() }
    }

}