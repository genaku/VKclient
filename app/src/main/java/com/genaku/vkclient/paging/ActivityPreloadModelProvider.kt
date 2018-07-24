package com.genaku.vkclient.paging

import android.app.Activity
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.genaku.vkclient.GlideApp

/**
 * Created by Gena Kuchergin on 23.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class ActivityPreloadModelProvider(val activity: Activity) : ListPreloader.PreloadModelProvider<String> {

    private var mUrlList = ArrayList<String>()

    fun setUrlList(uriList: ArrayList<String>) {
        mUrlList = uriList
    }

    override fun getPreloadRequestBuilder(url: String): RequestBuilder<*> =
            GlideApp.with(activity).load(url)

    override fun getPreloadItems(position: Int): MutableList<String> =
            mUrlList

}