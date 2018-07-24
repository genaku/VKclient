package com.genaku.vkclient.paging

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.genaku.vkclient.paging.delegates.interfaces.IAdapterDelegatesHolder
import com.genaku.vkclient.paging.delegates.interfaces.IAdapterViewItem

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
abstract class CompositePagedListAdapter<T : IAdapterViewItem>(
        private val delegatesHolder: IAdapterDelegatesHolder<T>,
        diffCallback: DiffUtil.ItemCallback<T>) : PagedListAdapter<T, RecyclerView.ViewHolder>(diffCallback) {

    override fun getItemViewType(position: Int): Int =
            getItem(position)?.viewType
                    ?: delegatesHolder.emptyViewType
                    ?: throw NullPointerException("can not find item view type for position $position (emptyViewType is not available)")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            delegatesHolder.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        delegatesHolder.onBindViewHolder(holder, item)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        val item = getItem(position) ?: return
        delegatesHolder.onBindViewHolder(holder, item, payloads)
    }

}