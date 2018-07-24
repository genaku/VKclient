package com.genaku.vkclient.paging

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.genaku.domain.interfaces.interactors.IGetNewsInteractor
import com.genaku.vkclient.uimodel.UIPostData
import java.util.concurrent.Executors

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class PagedListLiveDataFactory {

    companion object {

        private const val PAGE_SIZE = 10

        private val NETWORK_IO = Executors.newFixedThreadPool(5)

        private val defaultPagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(PAGE_SIZE)
                .build()

        fun createPagedPostFeedLiveData(
                interactor: IGetNewsInteractor
        ): LiveData<PagedList<UIPostData>> {
            val sourceFactory = PagedListSourceFactory(interactor)
            return LivePagedListBuilder(sourceFactory, defaultPagedListConfig)
                    .setFetchExecutor(NETWORK_IO)
                    .build()
        }

    }

}