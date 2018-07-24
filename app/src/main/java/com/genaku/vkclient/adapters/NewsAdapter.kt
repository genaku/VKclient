package com.genaku.vkclient.adapters

import android.graphics.drawable.Drawable
import com.genaku.vkclient.GlideRequest
import com.genaku.vkclient.paging.CompositePagedListAdapter
import com.genaku.vkclient.paging.PostDiffCallback
import com.genaku.vkclient.paging.delegates.*
import com.genaku.vkclient.uimodel.UIPostData

class NewsAdapter(
        glideRequest: GlideRequest<Drawable>,
        onClick: (UIPostData) -> Unit
) : CompositePagedListAdapter<UIPostData>(
        delegatesHolder = AdapterDelegatesHolder.Builder<UIPostData>()
                .setEmptyViewType(NewsViewType.PLACEHOLDER)
                .add(NewsViewType.POST, PostAdapterDelegate(glideRequest, onClick))
                .add(NewsViewType.REPOST, RepostAdapterDelegate(glideRequest, onClick))
                .build(),
        diffCallback = PostDiffCallback()
)