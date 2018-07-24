package com.genaku.vkclient.paging.delegates

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.genaku.vkclient.GlideRequest
import com.genaku.vkclient.R
import com.genaku.vkclient.hide
import com.genaku.vkclient.paging.delegates.interfaces.IAdapterDelegate
import com.genaku.vkclient.paging.delegates.viewholders.PostViewHolder
import com.genaku.vkclient.show
import com.genaku.vkclient.uimodel.UIPostData
import org.jetbrains.anko.image
import org.mym.plog.PLog

class PostAdapterDelegate(
        private val glideRequest: GlideRequest<Drawable>,
        private val onClick: (UIPostData) -> Unit
) : IAdapterDelegate<UIPostData> {

    override fun onCreateViewHolder(parent: ViewGroup): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: UIPostData) {
        bindItem(holder as PostViewHolder, item)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: UIPostData, payloads: MutableList<Any>) {
        bindItem(holder as PostViewHolder, item)
    }

    override fun onRecycled(holder: RecyclerView.ViewHolder) {
        (holder as PostViewHolder).apply {
            view.setOnClickListener {}
            imgAuthor.image = null
            imgMainPic.image = null
        }
    }

    private fun bindItem(holder: PostViewHolder, item: UIPostData) {
        holder.apply {
            tvText.text = item.text
            tvAuthor.text = item.authorName
            tvLikes.text = item.likesCount
            tvReposts.text = item.repostsCount
            tvDate.text = item.date
            glideRequest
                    .load(item.authorAvatarUrl)
                    .into(imgAuthor)
            if (item.pictures.size > 0) {
                imgMainPic.show()
                glideRequest
                        .load(item.pictures[0])
                        .into(imgMainPic)
            } else {
                imgMainPic.hide()
            }
            view.setOnClickListener { onClick(item) }
        }
    }

}