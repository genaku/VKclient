package com.genaku.vkclient.paging.delegates

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.genaku.vkclient.R
import com.genaku.vkclient.paging.delegates.interfaces.IAdapterDelegate
import com.genaku.vkclient.paging.delegates.viewholders.PlaceholderViewHolder
import com.genaku.vkclient.uimodel.UIPostData

/**
 * Created by Gena Kuchergin on 26.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class PlaceholderAdapterDelegate: IAdapterDelegate<UIPostData> {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.placeholder_item, parent, false)
        return PlaceholderViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: UIPostData) {
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: UIPostData, payloads: MutableList<Any>) {
    }

    override fun onRecycled(holder: RecyclerView.ViewHolder) {
    }
}