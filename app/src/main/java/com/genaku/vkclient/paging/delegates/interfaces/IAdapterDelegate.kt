package com.genaku.vkclient.paging.delegates.interfaces

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
interface IAdapterDelegate<TViewItem : IAdapterViewItem> {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: TViewItem)
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: TViewItem, payloads: MutableList<Any>)
    fun onRecycled(holder: RecyclerView.ViewHolder)
}