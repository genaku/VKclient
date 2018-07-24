package com.genaku.vkclient.paging.delegates

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.ViewGroup
import com.genaku.vkclient.paging.delegates.interfaces.IAdapterDelegate
import com.genaku.vkclient.paging.delegates.interfaces.IAdapterDelegatesHolder
import com.genaku.vkclient.paging.delegates.interfaces.IAdapterViewItem

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class AdapterDelegatesHolder<TViewItem : IAdapterViewItem>
private constructor(override val emptyViewType: Int?, private val adapterDelegates: SparseArray<IAdapterDelegate<TViewItem>>)
    : IAdapterDelegatesHolder<TViewItem> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            adapterDelegates.get(viewType).onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: TViewItem) =
            adapterDelegates.get(item.viewType).onBindViewHolder(holder, item)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: TViewItem, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, item)
            return
        }
        adapterDelegates.get(item.viewType).onBindViewHolder(holder, item, payloads)
    }

    class Builder<TViewItem : IAdapterViewItem> {

        private val mAdapterDelegates = SparseArray<IAdapterDelegate<TViewItem>>()
        private var mEmptyViewType: Int? = null

        fun setEmptyViewType(value: Int) = this.apply {
            mEmptyViewType = value
        }

        fun add(viewType: Int, delegate: IAdapterDelegate<TViewItem>)
                : Builder<TViewItem> = this.apply {
            mAdapterDelegates.put(viewType, delegate)
        }

        fun build(): AdapterDelegatesHolder<TViewItem> =
                AdapterDelegatesHolder(mEmptyViewType, mAdapterDelegates)

    }

}