package com.genaku.vkclient.paging

import android.arch.paging.DataSource
import com.genaku.domain.interfaces.interactors.IGetNewsInteractor
import com.genaku.vkclient.uimodel.UIPostData

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class PagedListSourceFactory(
        private val interactor: IGetNewsInteractor
) : DataSource.Factory<String, UIPostData>() {

    override fun create(): DataSource<String, UIPostData> {
        return PagedListDataSource(interactor)
    }

}