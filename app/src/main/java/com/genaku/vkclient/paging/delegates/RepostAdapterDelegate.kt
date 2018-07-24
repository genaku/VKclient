package com.genaku.vkclient.paging.delegates

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.genaku.vkclient.GlideRequest
import com.genaku.vkclient.R
import com.genaku.vkclient.hide
import com.genaku.vkclient.paging.delegates.interfaces.IAdapterDelegate
import com.genaku.vkclient.paging.delegates.viewholders.RepostViewHolder
import com.genaku.vkclient.show
import com.genaku.vkclient.uimodel.UIPostData
import org.jetbrains.anko.image
import org.mym.plog.PLog

class RepostAdapterDelegate(
        private val glideRequest: GlideRequest<Drawable>,
        private val onClick: (UIPostData) -> Unit
) : IAdapterDelegate<UIPostData> {

    override fun onCreateViewHolder(parent: ViewGroup): RepostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repost_item, parent, false)
        return RepostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: UIPostData) {
        bindItem(holder as RepostViewHolder, item)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: UIPostData, payloads: MutableList<Any>) {
        bindItem(holder as RepostViewHolder, item)
    }

    override fun onRecycled(holder: RecyclerView.ViewHolder) {
        (holder as RepostViewHolder).apply {
            view.setOnClickListener {}
            imgAuthor.image = null
            imgMainPic.image = null
            imgRepostedAuthor.image = null
        }
    }

    private fun bindItem(holder: RepostViewHolder, item: UIPostData) {
        holder.apply {
            tvText.text = item.text
            tvAuthor.text = item.authorName
            tvLikes.text = item.likesCount
            tvReposts.text = item.repostsCount
            tvDate.text = item.date
            glideRequest
                    .load(item.authorAvatarUrl)
                    .into(imgAuthor)
            val repost = item.copyHistory[0]
            if (repost.pictures.size > 0) {
                imgMainPic.show()
                glideRequest
                        .load(repost.pictures[0])
                        .into(imgMainPic)
            } else {
                imgMainPic.hide()
            }
            glideRequest
                    .load(repost.authorAvatarUrl)
                    .into(imgRepostedAuthor)
            tvRespostedAuthor.text = repost.authorName
            tvRepostedDate.text = repost.date
            tvRepostedText.text = repost.text
            view.setOnClickListener { onClick(item) }
        }
    }

}