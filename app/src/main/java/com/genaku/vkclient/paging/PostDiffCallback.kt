package com.genaku.vkclient.paging

import android.support.v7.util.DiffUtil
import com.genaku.vkclient.uimodel.UIPostData

class PostDiffCallback : DiffUtil.ItemCallback<UIPostData>() {

    override fun areItemsTheSame(oldItem: UIPostData, newItem: UIPostData): Boolean =
            oldItem.javaClass == newItem.javaClass && oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UIPostData, newItem: UIPostData): Boolean =
            oldItem == newItem

}